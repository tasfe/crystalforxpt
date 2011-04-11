package com.combanc.common.schedule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Pattern;

import com.combanc.common.util.Mapx;

/**
 * 定期检测计划任务是否完成，若己完成，则添加新的计划任务
 */
public class CronMonitor extends TimerTask {

	private boolean isRunning = false;// 当前执行过程是否结束

	private Timer mTimer = new Timer();// 真正用来执行任务的计时器

	private static Pattern P1 = Pattern.compile("\\d+", Pattern.DOTALL);// 指定数i

	private static Pattern P2 = Pattern.compile("\\d+\\-\\d+", Pattern.DOTALL);// 指定时间段a-b

	private static Pattern P3 = Pattern.compile("(((\\d+\\-\\d+)|\\d+)(,|$))+", Pattern.DOTALL);// 指定离散的几个值

	private static Pattern P4 = Pattern.compile("((\\d+\\-\\d+)|\\*)\\/\\d+", Pattern.DOTALL);// 步进表达式

	/**
	 * 本方法每隔指定时间即执行一次
	 */
	public void run() {
		if (!isRunning) {
			runMain();
		}
	}

	/**
	 * 定期执行主流程. 若Cron任务定义文件有改动，则终止当前任务序列的执行，重新安排文件中定义的任务。<br>
	 * 若没有改动，则检查任务序列中有没有任务是刚执行完的，若有，则为其安排下次执行
	 */
	private synchronized void runMain() {
		if (!isRunning) {
			isRunning = true;
			try {
				Mapx map = CronManager.getInstance().getManagers();
				Object[] vs = map.valueArray();
				for (int i = 0; i < map.size(); i++) {
					AbstractTaskManager tm = (AbstractTaskManager) vs[i];
					Mapx tmap = tm.getUsableTasks();
					Object[] ks = tmap.keyArray();
					for (int j = 0; j < tmap.size(); j++) {
						try {
							long id = Long.parseLong(ks[j].toString());
							if (isOnTime(tm.getTaskCronExpression(id)) && !tm.isRunning(id)) {
								tm.execute(id);
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				isRunning = false;
			}
		}
	}

	private static boolean isOnTime(String cronExpr) {
		try {
			Date d = getNextRunTime(cronExpr);
			long t = d.getTime();
			if (t > System.currentTimeMillis() && t - System.currentTimeMillis() < CronManager.getInstance().getInterval()) {
				return true;
			}
		} catch (CronExpressionException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Minute:0-59<br>
	 * Hour:0-23<br>
	 * Day:1-31<br>
	 * Month:1-12<br>
	 * Week:1-7<br>
	 */
	public static int[] getSuitNumbers(String exp, int min, int max) throws CronExpressionException {
		ArrayList list = new ArrayList();
		if (P1.matcher(exp).matches()) {
			int v = Integer.parseInt(exp);
			v = v > max ? max : v;
			v = v < min ? min : v;
			list.add(new Integer(v));
		} else if (P2.matcher(exp).matches()) {
			String[] arr = exp.split("\\-");
			int[] is = new int[] { Integer.parseInt(arr[0]), Integer.parseInt(arr[1]) };
			is[0] = is[0] > max ? max : is[0];
			is[1] = is[1] > max ? max : is[1];
			is[0] = is[0] < min ? min : is[0];
			is[1] = is[1] < min ? min : is[1];
			if (is[0] > is[1]) {
				for (int j = is[0]; j <= max; j++) {
					list.add(new Integer(j));
				}
				for (int j = min; j <= is[1]; j++) {
					list.add(new Integer(j));
				}
			} else {
				for (int j = is[0]; j <= is[1]; j++) {
					list.add(new Integer(j));
				}
			}
		} else if (P3.matcher(exp).matches()) {
			String[] arr = exp.split(",");
			for (int i = 0; i < arr.length; i++) {
				String str = arr[i];
				if (str.indexOf('-') > 0) {
					String[] arr2 = str.split("\\-");
					int[] tmp = new int[] { Integer.parseInt(arr2[0]), Integer.parseInt(arr2[1]) };
					tmp[0] = tmp[0] > max ? max : tmp[0];
					tmp[1] = tmp[1] > max ? max : tmp[1];
					tmp[0] = tmp[0] < min ? min : tmp[0];
					tmp[1] = tmp[1] < min ? min : tmp[1];
					if (tmp[0] > tmp[1]) {
						for (int j = tmp[0]; j <= max; j++) {
							list.add(new Integer(j));
						}
						for (int j = min; j <= tmp[1]; j++) {
							list.add(new Integer(j));
						}
					} else {
						for (int j = tmp[0]; j <= tmp[1]; j++) {
							list.add(new Integer(j));
						}
					}
				} else {
					list.add(new Integer(Integer.parseInt(str)));
				}
			}
		} else if (P4.matcher(exp).matches()) {
			String[] arr = exp.split("\\/");
			int step = Integer.parseInt(arr[1]);
			int[] is = new int[2];
			if (arr[0].equals("*")) {
				is[0] = min;
				is[1] = max;
			} else {
				arr = arr[0].split("\\-");
				is = new int[] { Integer.parseInt(arr[0]), Integer.parseInt(arr[1]) };
				is[0] = is[0] > max ? max : is[0];
				is[1] = is[1] > max ? max : is[1];
				is[0] = is[0] < min ? min : is[0];
				is[1] = is[1] < min ? min : is[1];
			}
			int cm = is[1];
			int len = (max - min) + 1;
			if (is[0] > is[1]) {
				cm = is[1] + len;
			}
			for (int i = is[0]; i <= cm; i += step) {
				list.add(new Integer(i > max ? i - len : i));
			}
		} else if (exp.equals("*")) {
			for (int i = min; i <= max; i++) {
				list.add(new Integer(i));
			}
		} else {
			throw new CronExpressionException("错误的Cron表达式");
		}
		int[] arr = new int[list.size()];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = ((Integer) list.get(i)).intValue();
		}
		Arrays.sort(arr);
		return arr;
	}

	private static Date getNextRunTime(Date lastDate, String cronExpression) throws CronExpressionException {
		String[] expArr = cronExpression.split("\\s");
		if (expArr.length < 5) {
			throw new CronExpressionException("错误的Cron表达式");
		}
		Calendar c = Calendar.getInstance();
		c.setTime(lastDate);
		c.add(Calendar.MINUTE, 1);

		// 月份
		int month = c.get(Calendar.MONTH) + 1;
		int[] ms = getSuitNumbers(expArr[3], 1, 12);
		int mi = -1;
		boolean carryFlag = false;
		boolean flag = false;
		for (int i = 0; i < ms.length; i++) {
			if (ms[i] == month) {
				mi = i;
				flag = true;
				break;
			}
			if (ms[i] > month) {
				c.set(Calendar.MONTH, ms[i] - 1);
				carryFlag = true;
				mi = i;
				flag = true;
				break;
			}
		}
		if (!flag) {
			mi = 0;
			c.set(Calendar.MONTH, ms[mi] - 1);
			c.add(Calendar.YEAR, 1);
			carryFlag = true;
		}

		// 天数
		int day = c.get(Calendar.DAY_OF_MONTH);
		int[] ds = getSuitNumbers(expArr[2], 1, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		int di = -1;
		if (carryFlag) {
			di = 0;
			c.set(Calendar.DAY_OF_MONTH, ds[0]);
		} else {
			flag = false;
			for (int i = 0; i < ds.length; i++) {
				if (ds[i] == day) {
					di = i;
					flag = true;
					break;
				}
				if (ds[i] > day) {
					c.set(Calendar.DAY_OF_MONTH, ds[i]);
					carryFlag = true;
					di = i;
					flag = true;
					break;
				}
			}
			if (!flag) {
				c.set(Calendar.DAY_OF_MONTH, ds[0]);
				if (mi != ms.length - 1) {
					mi++;
				} else {
					mi = 0;
					c.add(Calendar.YEAR, 1);
				}
				c.set(Calendar.MONTH, ms[mi] - 1);
				carryFlag = true;
				di = 0;
			}
		}

		// 小时
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int[] hs = getSuitNumbers(expArr[1], 0, 23);
		int hi = -1;
		if (carryFlag) {
			hi = 0;
			c.set(Calendar.HOUR_OF_DAY, hs[0]);
		} else {
			flag = false;
			for (int i = 0; i < hs.length; i++) {
				if (hs[i] == hour) {
					hi = i;
					flag = true;
					break;
				}
				if (hs[i] > hour) {
					c.set(Calendar.HOUR_OF_DAY, hs[i]);
					carryFlag = true;
					hi = i;
					flag = true;
					break;
				}
			}
			if (!flag) {
				c.set(Calendar.HOUR_OF_DAY, hs[0]);
				if (di != ds.length - 1) {
					di++;
				} else {
					di = 0;
					if (mi != ms.length - 1) {
						mi++;
					} else {
						mi = 0;
						c.add(Calendar.YEAR, 1);
					}
					c.set(Calendar.MONTH, ms[mi] - 1);
				}
				c.set(Calendar.DAY_OF_MONTH, ds[di]);
				carryFlag = true;
				hi = 0;
			}
		}

		// 分钟
		int minute = c.get(Calendar.MINUTE);
		int[] fs = getSuitNumbers(expArr[0], 0, 59);
		if (carryFlag) {
			c.set(Calendar.MINUTE, fs[0]);
		} else {
			flag = false;
			for (int i = 0; i < fs.length; i++) {
				if (fs[i] == minute) {
					flag = true;
					break;
				}
				if (fs[i] > minute) {
					c.set(Calendar.MINUTE, fs[i]);
					carryFlag = true;
					flag = true;
					break;
				}
			}
			if (!flag) {
				c.set(Calendar.MINUTE, fs[0]);
				if (hi != hs.length - 1) {
					hi++;
				} else {
					if (di != ds.length - 1) {
						di++;
					} else {
						di = 0;
						if (mi != ms.length - 1) {
							mi++;
						} else {
							mi = 0;
							c.add(Calendar.YEAR, 1);
						}
						c.set(Calendar.MONTH, ms[mi] - 1);
					}
					hi = 0;
				}
				c.set(Calendar.HOUR, hs[hi]);
				carryFlag = true;
			}
		}

		// 星期
		int week = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (week == 0) {
			week = 7;
		}
		int[] ws = getSuitNumbers(expArr[4], 1, 7);
		flag = false;
		for (int i = 0; i < ws.length; i++) {
			if (ws[i] == week) {
				flag = true;
				break;
			}
		}
		if (!flag) {
			c.add(Calendar.DAY_OF_MONTH, 1);
			return getNextRunTime(c.getTime(), cronExpression);
		}
		c.set(Calendar.SECOND, 0);
		return c.getTime();
	}

	public static Date getNextRunTime(String cronExpression) throws CronExpressionException {
		return getNextRunTime(new Date(), cronExpression);
	}

	public void destory() {
		this.cancel();
		mTimer.cancel();
	}
}

