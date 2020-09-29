/**
 * paging tool
 */

var paging_tool={
	current_page_num: 1,
	total_page_num: 1,
	page_item_size: 10,
	item_total_size: 0,
	allPageQuery: function(){},
	everyPageQuery: function(current_page_num){},
	init: function(){
		this.total_page_num=Math.ceil(this.item_total_size/this.page_item_size);
	},
	id: null,
	gotoPage: function(current_page_num){
		current_page_num=parseInt(current_page_num);
		console.log("current_page_num==="+current_page_num);
		if(!current_page_num || isNaN(current_page_num) || current_page_num>this.total_page_num || current_page_num<1){
			//alert("please input a right number!");
			return;
		}
		console.log("current_page_num====="+current_page_num);
		this.everyPageQuery(current_page_num);
		this.current_page_num=current_page_num;
		
		var current_page_num_=this.current_page_num;
		var this_=this;
		////d start
		var d=document.getElementById(this.id);
		d.innerHTML="";
		var prev=document.createElement('button');
		prev.setAttribute('id','page-prev');
		prev.innerText='上一页';
		prev.onclick=function(){
			if(--current_page_num_<1)current_page_num_=1;
			this_.gotoPage(current_page_num_);
		}
		d.append(prev);
		if(this.total_page_num<=5){
			for(var i=1;i<=this.total_page_num;i++){
				var b=document.createElement('button');
				b.setAttribute("id",'page-b'+i);
				b.innerText=i;
				d.append(b);
			}
		}else{
			if(this.current_page_num<=3){
				for(var i=1;i<=4;i++){
					var b=document.createElement('button');
					b.setAttribute("id",'page-b'+i);
					b.innerText=i;
					d.append(b);
				}
				d.append("...");//...
				var b=document.createElement('button');
				b.setAttribute("id",'page-b'+this.total_page_num);
				b.innerText=this.total_page_num;
				d.append(b);//
			}else if(this.current_page_num>=this.total_page_num-2){
				var b=document.createElement('button');
				b.setAttribute("id",'page-b1');
				b.innerText=1;
				d.append(b);///b1
				d.append("...");//...
				for(var i=this.total_page_num-3;i<=this.total_page_num;i++){
					var b=document.createElement('button');
					b.setAttribute("id",'page-b'+i);
					b.innerText=i;
					d.append(b);
				}
			}else{
				var b=document.createElement('button');
				b.setAttribute("id",'page-b1');
				b.innerText=1;
				d.append(b);///b1
				d.append("...");//...
				for(var i=this.current_page_num-1;i<=this.total_page_num+1;i++){
					var b=document.createElement('button');
					b.setAttribute("id",'page-b'+i);
					b.innerText=i;
					d.append(b);
				}
				d.append("...");//...
				var b=document.createElement('button');
				b.setAttribute("id",'page-b'+this.total_page_num);
				b.innerText=this.total_page_num;
				d.append(b);//
			}
		}
			
		var next=document.createElement('button');
		next.setAttribute('id','page-next');
		next.innerText='下一页';
		next.onclick=function(){
			if(++current_page_num_>this_.total_page_num)current_page_num_=this_.total_page_num;
			this_.gotoPage(current_page_num_);
		}
		d.append(next);
		
		var goto=document.createElement('input');
		goto.setAttribute('id','goto-input');
		d.append(goto);
		var goto_button=document.createElement('button');
		goto_button.innerText='goto';
		goto_button.onclick=function(){
			var current_page_=document.getElementById('goto-input').value;
			if(!current_page_ || isNaN(current_page_) || current_page_>this_.total_page_num || current_page_<1){
				alert("please input a right number!");
				return;
			}
			this_.gotoPage(current_page_);
		}
		d.append(goto_button);
		////d end
		
		$("#page-b"+current_page_num).css("background-color","red").siblings("button[id^='page-b']").css("background-color","white");
		$("button[id^='page-b']").click(function(){
			var current_page=Number($(this).attr('id').replace('page-b',''));
			this_.gotoPage(current_page);
		});
	}
}