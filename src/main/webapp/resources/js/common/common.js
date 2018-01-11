$(function() {
	$("#docSearchText").focus(function(){
		$("#docSearchText").attr("class", "text active");
	});
	
	$("#docSearchText").blur(function(){
		$("#docSearchText").attr("class", "text");
	});
	
	$("#docSearchText").keydown(function(e){
		e = e || event;
		if (e.keyCode == 13) {
			locSearch();
		}
	});
});

/**
 * 用于兼容不支持trim方法的浏览器
 */
String.prototype.trim = function() {
	var str = this, str = str.replace(/^\s /, '');
	for (var i = str.length - 1; i >= 0; i--) {
		if (/\S/.test(str.charAt(i))) {
			str = str.substring(0, i + 1);
			break;
		}
	}
	return str;
}

/**
 * 
 * @param url 访问地址
 * @param type 访问类型
 * @param parameter 参数值
 * @returns
 */
function ajaxCommon(url,type,parameter, callBack){
	var dataObj=null;
	 $.ajax({
	        dataType: "json",
	        type: type,
	        data: parameter,
	        cache: false,
	        async: false,  
	        url: url,
	        error: function(){
                if (callBack != null) {
                    callBack();
                }
	        },
	        success: function(data){
	        	dataObj=data;
	        }
	    });
	 return dataObj;
}

/**
 * 
 * @param url 访问地址
 * @param type 访问类型
 * @param parameter 参数值
 * @returns
 */
function ajaxCommonForJson(url,type,parameter, callBack){
	var dataObj=null;
	 $.ajax({
	        dataType: "json",
	        type: type,
	        contentType:"application/json", 
	        data: JSON.stringify(parameter),
	        cache: false,
	        async: false,  
	        url: url,
	        error: function(){
	        	if (callBack != null) {
                    callBack();
				}

	        },
	        success: function(data){
	        	dataObj=data;
	        }
	    });
	 return dataObj;
}

/**
 * 获取今天的日期
 * 格式：yyyy-MM-dd
 * @returns
 */
function getCurrentDate(){
	var myDate = new Date(); 
	var tt = myDate.toLocaleDateString();
	return tt.replace(/\//g, "-");
}

/**
 * 
 * 返回相对于date的nDay天之前/之后的日期
 * 格式：yyyy-MM-dd
 * @param date Date对象
 * @param nDays 整数为nDays之后，负数为nDays之前的日期
 * @returns
 */
function addOrReduceDayForCurrentDate(date, nDays){
	var nextDate = new Date(date.getTime() + nDays*24*60*60*1000); 
	var tt = nextDate.toLocaleDateString();
	return tt.replace(/\//g, "-");
}

/**
 * 使按钮不可用
 * @param btnId
 */
function makeBtnDisabled(btnId){
	$("#" + btnId).attr("class", "btn btn-gray");
	$("#" + btnId).prop("disabled", true);
}

/**
 * 使按钮可用
 * @param btnId
 */
function makeBtnAbled(btnId){
	$("#" + btnId).prop("disabled", false);
	$("#" + btnId).attr("class", "btn btn-blue");
}

/**
 * 跳转到搜索页面
 * @returns
 */
function locSearch() {
	window.open("/elastic/index?searchText=" + $('#docSearchText').val());
}

/**
 * 
 * 生成分页页签
 * @param ulId 分页ul标签的ID值
 * @param pageIndex 当前页码
 * @param pageSize 每页多少条
 * @param totalPage 总共多少页
 * @param totalCount 总共多少条
 * @param bindFunction 页码点击事件函数
 */
function createPaging(ulId, pageIndex, pageSize, totalPage, totalCount, bindFunction){
	//删除旧页码
	$("#"+ ulId +" a").remove();
	var midNum = 4;//中间最多显示几个页码
	
	//有0页不显示
	if(totalPage < 1){
		return;
	}
	
	var liStr = "";
	//生成上一页、和第一页
	if(pageIndex == 1){
		liStr = liStr + "<a class='pn-prev disabled' href='javascript:void(0)'><i>&lt;</i></a>";
		liStr = liStr + "<a class='curr' href='javascript:void(0)'>1</a>";
	}else{
		liStr = liStr + "<a class='pn-prev' href='javascript:void(0)' onclick='"+ bindFunction +"("+ (pageIndex - 1) +")'><i>&lt;</i></a>";
		liStr = liStr + "<a href='javascript:void(0)' onclick='"+ bindFunction +"(1)'>1</a>";
	}
	
	//左侧的省略号
	if((pageIndex - midNum) > 2){
		liStr = liStr + "<a class='pn-break' href='javascript:void(0)' onclick='"+ bindFunction +"("+ (pageIndex - midNum - 1) +")'>...</a>";
	}
	
	//前N页
	for(var i = midNum; i > 0; i--){
		if(pageIndex - i > 1){
			liStr = liStr + "<a href='javascript:void(0)' onclick='"+ bindFunction +"("+ (pageIndex - i) +")'>"+ (pageIndex - i) +"</a>";
		}
	}
	
	//当前页
	if((pageIndex != 1) && (pageIndex != totalPage)){
		liStr = liStr + "<a class='curr' href='javascript:void(0)' onclick='"+ bindFunction +"("+ (pageIndex) +")'>"+ (pageIndex) +"</a>";
	}
	
	//后N页
	for(var i =1; i < midNum+1; i++){
		if((pageIndex + i) < totalPage){
			liStr = liStr + "<a href='javascript:void(0)' onclick='"+ bindFunction +"("+ (pageIndex + i) +")'>"+ (pageIndex + i) +"</a>";
		}
	}
	
	//右边的省略号
	if((pageIndex + midNum + 1) < totalPage){
		liStr = liStr + "<a class='pn-break' href='javascript:void(0)' onclick='"+ bindFunction +"("+ (pageIndex + midNum + 1) +")'>...</a>";
	}
	
	//下一页和最后一页
	if(pageIndex == totalPage){
		if (totalPage != 1) {
			liStr = liStr + "<a class='curr' href='javascript:void(0)'>"+ totalPage +"</a>";
		}
		liStr = liStr + "<a class='pn-next disabled' href='javascript:void(0)'><i>&gt;</i></a>";
	}else{
		if (totalPage != 1) {
			liStr = liStr + "<a href='javascript:void(0)' onclick='"+ bindFunction +"("+ totalPage +")'>"+ totalPage +"</a>";
		}
		liStr = liStr + "<a class='pn-next' href='javascript:void(0)' onclick='"+ bindFunction +"("+ (pageIndex + 1) +")'><i>&gt;</i></a>";
	}
	
	$("#" + ulId).append(liStr);
}

/**
 * 获取cookie
 *
 * @param c_name
 * @returns {*}
 */
function getCookie(c_name){
    if (document.cookie.length>0){　　//先查询cookie是否为空，为空就return ""
        c_start=document.cookie.indexOf(c_name + "=");　　//通过String对象的indexOf()来检查这个cookie是否存在，不存在就为 -1　　
        if (c_start!=-1){
            c_start=c_start + c_name.length+1;　　//最后这个+1其实就是表示"="号啦，这样就获取到了cookie值的开始位置
            c_end=document.cookie.indexOf(";",c_start)　　//其实我刚看见indexOf()第二个参数的时候猛然有点晕，后来想起来表示指定的开始索引的位置...这句是为了得到值的结束位置。因为需要考虑是否是最后一项，所以通过";"号是否存在来判断
            if (c_end==-1) c_end=document.cookie.length;
            return unescape(document.cookie.substring(c_start,c_end));　　//通过substring()得到了值。想了解unescape()得先知道escape()是做什么的，都是很重要的基础，想了解的可以搜索下，在文章结尾处也会进行讲解cookie编码细节
        }
    }
    return "";
}　　