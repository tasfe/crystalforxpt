/**
 * author:hqwang
 * QQ:254435817
 **/
function dynPictureNews(container){
	var _self = this;
	
	this.container = container;
	this.index = 0;
	
	this.add = function(src, href, target){
		if(!this.imgs) this.imgs = [];
		this.imgs.push({"src":src, "href":href, "target":target});
	}
	
	this.create = function() {
		this.container.style.position = "relative";
		
		if(!this.img)this.img = document.createElement("img");
		this.container.appendChild(this.img);
		this.img.style.width = this.img.style.width || this.container.style.width;
		this.img.style.height = this.img.style.height || this.container.style.height;

		if(this.imgs) {
			this.img.src = this.imgs[0].src;
			if(this.imgs[0].href) {
				this.img.onmouseover = function(){this.style.cursor = "pointer"};
				this.img.onclick = this.link(this.imgs[0].href, this.imgs[0].target);
			}
			
			if(this.imgs.length >1) {
				if(!this.ul)this.ul = document.createElement("ul");
				this.container.appendChild(this.ul);
				this.ul.style.position = "absolute";
				
				if(!this.lis)this.lis = [];
				for(var i=0; i<this.imgs.length; i++) {
					var li = document.createElement("li");
					this.ul.appendChild(li);
					this.lis.push(li);
					li.innerHTML = i+1;
					if(i == 0) {
						li.className = "cur";	
					} else {
						li.className = "";
						li.onclick = function(){_self.clickNav(this);};
					}
				}
			}
		}
	}
	
	this.fadeOutStep = [1, 0.9, 0.7, 0.4, 0.1];
	this.fadeInStep = [0.1, 0.4, 0.7, 0.9, 1];
	this.fadeTime = 100;
	
	this.change = function(cur) {
		fade(_self.img, _self.fadeOutStep, _self.fadeTime, function () {
			for(var index in _self.lis) {
				var li = _self.lis[index]
				if(li == cur) {
					_self.index = index;
					_self.img.src = _self.imgs[index].src;
					if(_self.imgs[index].href) {
						_self.img.onmouseover = function(){this.style.cursor = "pointer"};
						_self.img.onclick = _self.link(_self.imgs[index].href, _self.imgs[index].target);
					} else {
						_self.img.onmouseover = function(){this.style.cursor = "default"};
						_self.img.onclick = "";
					}
					cur.className = "cur";
					cur.onclick = "";
				} else if(li.className == "cur") {
					li.className = "";
					li.onclick = function (){_self.clickNav(this);};
				}
			}
			fade(_self.img, _self.fadeInStep, _self.fadeTime);
		});
	}
	
	this.link = function(href, target) {
		return function() {
			if(target == "_blank") {
				window.open(href);
			} else if(target == "_top") {
				top.location = href;	
			} else if(top == "_parent") {
				parent.location = href;	
			} else {
				self.location = href;
			}
		};
	}
	
	this.clickNav = function(cur) {
		cur.onclick = "";
		this.stop();
		this.change(cur);
		this.play();
	}
	
	this.play = function(time) {
		if(!this.lis)return;
		if(!this.change_interval)this.change_interval = time;
		if(!this.change_interval || typeof this.change_interval != "number")return;
		this.interval = setInterval(function(){
			_self.change(_self.lis[(++_self.index)%_self.lis.length]);
		}, this.change_interval);
	}
	
	this.stop = function(){
		if(!this.interval) return;
		clearInterval(this.interval);
	}
}