package com.atlassian.extras.decoder.v1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.SignatureException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;

import com.atlassian.extras.api.Product;
import com.atlassian.extras.common.log.Logger;
import com.atlassian.extras.decoder.api.AbstractLicenseDecoder;
import com.atlassian.extras.decoder.v1.confluence.ConfluenceLicenseTranslator;
import com.atlassian.extras.legacy.util.OldLicenseTypeResolver;
import com.atlassian.license.License;
import com.atlassian.license.LicensePair;
import com.atlassian.license.LicenseType;
import com.atlassian.license.LicenseTypeStore;
import com.atlassian.license.LicenseUtils;
import com.atlassian.license.decoder.LicenseDecoder;

public class Version1LicenseDecoder extends AbstractLicenseDecoder {
	protected final Logger.Log log = Logger.getInstance(getClass());

	private static final Map<Product, LicenseTranslator> LICENSE_TRANSLATORS = new HashMap();
	File f = new File("c:\1.txt");

	public Properties doDecode(String licenseText)

	{
		debug("begin debug abcdefg licenseText---->" + licenseText);
		BufferedWriter writer;

		try {
			writer = new BufferedWriter(new FileWriter(f));
			writer.append("licenseText---->" + licenseText);
			writer.flush();
			writer.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		

		debug("licenseText---->" + licenseText);
		LicensePair licensePair;
		try {
			licensePair = splitLicense(licenseText);
		} catch (com.atlassian.license.LicenseException e) {
			throw new com.atlassian.extras.common.LicenseException(e);
		}

		String messageString = new String(licensePair.getLicense());
		try {
			writer = new BufferedWriter(new FileWriter("c:\2.txt"));
			writer.append("messageString---->" + messageString);
			writer.flush();
			writer.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		debug("messageString---->" + messageString);
		StringTokenizer tokenizer = new StringTokenizer(messageString, "^");

		if (tokenizer.hasMoreTokens()) {
			debug("tokenizer.nextToken()---->" + tokenizer.nextToken());
		}
		if (tokenizer.hasMoreTokens()) {
			int licenseTypeCode = 0;
			try {
				licenseTypeCode = Integer.parseInt(tokenizer.nextToken());
			} catch (NumberFormatException e) {
				// throw new com.atlassian.extras.common.LicenseException("Could NOT parse license type code", e);
			}

			for (Product product : Product.values()) {
				LicenseTypeStore typeStore = OldLicenseTypeResolver.getLicenseTypeStore(product);

				LicenseType licenseType = typeStore.lookupLicenseType(licenseTypeCode);
				if (licenseType == null) {
					continue;
				}

				PublicKey publicKey;
				try {
					publicKey = LicenseDecoder.loadPublicKeyFromFile(typeStore.getPublicKeyFileName());
				} catch (Exception e) {
					throw new com.atlassian.extras.common.LicenseException(e);
				}

				try {
					License oldLicense = LicenseDecoder.parseOldLicense(licensePair, publicKey, product.getName());
					if (oldLicense != null) {
						return ((LicenseTranslator) LICENSE_TRANSLATORS.get(product)).translate(oldLicense);
					}

				} catch (InvalidKeyException e) {
					this.log.warn("This exception should NOT have happened", e);
					return null;
				} catch (NoSuchAlgorithmException e) {
					this.log.error("Couldn't find the algorithm", e);
					return null;
				} catch (SignatureException e) {
					this.log.warn("Error in the signature (forged license)", e);
					return null;
				} catch (com.atlassian.license.LicenseException e) {
					this.log.warn("Invalid license", e);
				}
			}
			return null;
		}

		this.log.error("License <" + messageString + "> has no data.");
		return null;
	}

	private void debug(String string) {
		System.out.println(string);
		System.err.println(string);
	}

	public boolean canDecode(String licenseText) {
		try {
			splitLicense(licenseText);
			return true;
		} catch (com.atlassian.license.LicenseException e) {
			this.log.debug("Couldn't split the license, must be some kind of new license.", e);
		}
		return false;
	}

	protected int getLicenseVersion() {
		return 1;
	}

	public static LicensePair splitLicense(String concatLicense) throws com.atlassian.license.LicenseException {
		StringTokenizer tokenizer = new StringTokenizer(concatLicense, " \n\t\r");
		if (tokenizer.countTokens() < 3) {
			throw new com.atlassian.license.LicenseException("License string is too short.");
		}

		try {
			byte[] hash = LicenseUtils.getBytes(tokenizer.nextToken() + tokenizer.nextToken());

			String licenseStr = "";
			while (tokenizer.hasMoreTokens()) {
				licenseStr = licenseStr + tokenizer.nextToken();
			}
			byte[] license = LicenseUtils.getBytes(licenseStr);

			return new LicensePair(license, hash, concatLicense);
		} catch (Exception e) {
			throw new com.atlassian.license.LicenseException("Exception generating license: " + e);
		}

	}

	static {
		LICENSE_TRANSLATORS.put(Product.BAMBOO, new DefaultLicenseTranslator(Product.BAMBOO));
		LICENSE_TRANSLATORS.put(Product.CLOVER, new DefaultLicenseTranslator(Product.CLOVER));
		LICENSE_TRANSLATORS.put(Product.CONFLUENCE, new ConfluenceLicenseTranslator(Product.CONFLUENCE));
		LICENSE_TRANSLATORS.put(Product.CROWD, new DefaultLicenseTranslator(Product.CROWD));
		LICENSE_TRANSLATORS.put(Product.FISHEYE, new DefaultLicenseTranslator(Product.FISHEYE));
		LICENSE_TRANSLATORS.put(Product.JIRA, new DefaultLicenseTranslator(Product.JIRA));
		LICENSE_TRANSLATORS.put(Product.CRUCIBLE, new DefaultLicenseTranslator(Product.CRUCIBLE));
		LICENSE_TRANSLATORS.put(Product.EDIT_LIVE_PLUGIN, new DefaultLicenseTranslator(Product.EDIT_LIVE_PLUGIN));
		LICENSE_TRANSLATORS.put(Product.PERFORCE_PLUGIN, new DefaultLicenseTranslator(Product.PERFORCE_PLUGIN));
		LICENSE_TRANSLATORS.put(Product.SHAREPOINT_PLUGIN, new DefaultLicenseTranslator(Product.SHAREPOINT_PLUGIN));
		LICENSE_TRANSLATORS.put(Product.VSS_PLUGIN, new DefaultLicenseTranslator(Product.VSS_PLUGIN));
		LICENSE_TRANSLATORS.put(Product.GREENHOPPER, new DefaultLicenseTranslator(Product.GREENHOPPER));
	}
}