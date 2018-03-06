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

/**
 * 获取最终使用的担保
 *
 * @param guaranteeRules	担保规则列表
 * @param lastArrivalTime	最晚到店时间
 * @param roomNum			预订房间数量
 * @param arrivalDate		到店日期
 * @param departureDate		离店日期
 */
function getGuaranteeDescription(guaranteeRules, nightlyRates, lastArrivalTime, roomNum, arrivalDate, departureDate) {
	// 担保金额
	var amount = 0.0;
	// 描述
	var description = "";
	// 最晚取消时间
	var lastCancelTime = -1;
	var fullAmount = 0.0;

	if (guaranteeRules == null || guaranteeRules.length < 1 || nightlyRates == null || nightlyRates.length < 1) {
		var result = {
            "amount": 0.0,
            "cancelTime": -1,
            "description": "在入住前可以随时取消。"
		};
		return result;
    }

	var numOfDay = (departureDate-arrivalDate)/(3600*1000*24);
    for (var i = 0; i < numOfDay; i++) {
        var thisDay = arrivalDate + (3600*1000*24*i);
        for (var j = 0; j < guaranteeRules.length; j ++) {
            var guaranteeRule = guaranteeRules[j];
            if (thisDay >= guaranteeRule.startDate && thisDay <= guaranteeRule.endDate) {
                // 入住日担保是第一天并且当前不是第一天则可以直接跳过
                if ( guaranteeRule.dateType == "CheckInDay" && i != 0) {
                	break;
                }
                // 星期不符合直接跳过
                var week = moment(thisDay).day();
                if (guaranteeRule.weekSet.indexOf(week) < 0) {
                	break;
				}
				var isGuarantee = false;
				if (guaranteeRule.isTimeGuarantee == false && guaranteeRule.isAmountGuarantee == false) {
                    isGuarantee = true;
				} else {
                    if (guaranteeRule.isTimeGuarantee == true) {
                        var startTimeString = moment(arrivalDate).format("YYYY-MM-DD") + " " + guaranteeRule.startTime;
                        var startTime = moment(startTimeString, "YYYY-MM-DD H:mm").valueOf();
                        var endTimeString = moment(arrivalDate).format("YYYY-MM-DD") + " " + guaranteeRule.endTime;
                        var endTime = moment(endTimeString, "YYYY-MM-DD H:mm").valueOf();
                        if (guaranteeRule.isTomorrow) {
                            endTime = moment(endTime).add(1, "days").valueOf();
						}
						if (lastArrivalTime >= startTime && lastArrivalTime <= endTime) {
                            isGuarantee = true;
						}
                    }
                    if (guaranteeRule.isAmountGuarantee == true && roomNum >= guaranteeRule.amount) {
                        isGuarantee = true;
                    }
				}
				// 判断后不需要担保则跳出
				if (isGuarantee == false) {
					break;
				}

				// 最晚取消时间逻辑
				if (lastCancelTime != 0) {
                    if (guaranteeRule.changeRule == "NoChange") {
                        lastCancelTime = 0;
                    } else if (guaranteeRule.changeRule == "NeedSomeDay") {
                    	var cancelTimeString = moment(guaranteeRule.day).format("YYYY-MM-DD") + " " + guaranteeRule.time;
                    	var cancelTime = moment(cancelTimeString, "YYYY-MM-DD HH:mm").valueOf();
                    	if (cancelTime > lastCancelTime) {
                            lastCancelTime = cancelTime;
						}
                    } else if (guaranteeRule.changeRule == "NeedCheckinTime") {
                    	var cancelTime = moment(lastArrivalTime).add(-guaranteeRule.hour, "hours").valueOf();
                        if (cancelTime > lastCancelTime) {
                            lastCancelTime = cancelTime;
                        }
                    } else if (guaranteeRule.changeRule == "NeedCheckin24hour") {
                        var cancelTime = moment(arrivalDate).add(24-guaranteeRule.hour, "hours").valueOf();
                        if (cancelTime > lastCancelTime) {
                            lastCancelTime = cancelTime;
                        }
                    }
				}

				// 担保金额逻辑
				if (guaranteeRule.guaranteeType == "FirstNightCost") {
                    amount += nightlyRates[0].member;
				} else if (guaranteeRule.guaranteeType == "FullNightCost") {
					for (var k = 0; k < nightlyRates.length; k++) {
                        fullAmount += nightlyRates[k].member;
					}
				} else if (guaranteeRule.guaranteeType == "SingleNightCost") {
                    amount += nightlyRates[i].member;
				}
			}
		}

		if (fullAmount > 0) {
            amount = fullAmount;
		}

		if (amount > 0) {
            description = "您需要支付" + amount + "元进行担保,";
		}
		if (lastCancelTime == -1) {
            description += "在入住前可以随时取消。"
		} else if (lastCancelTime == 0) {
            description += "一经预订不可取消。";
		} else {
        	if (moment(cancelTime).valueOf() <= moment().valueOf()) {
                description += "一经预订不可取消。";
			} else {
                description += "在"+ moment(cancelTime).format("YYYY年MM月DD日HH时mm分") + "前免费取消，之后取消将扣除所有担保金额。";
			}
		}

		var result = {
        	"amount": amount,
			"cancelTime": cancelTime,
			"description": description
		};
        return result;
	}
}

/**
 * 最终使用的预付规则
 * @param prepayRules		预付规则列表
 * @param nightlyRates		每夜价格列表
 * @param lastArrivalTime	最晚到店时间
 * @param roomNum			预订房间数量
 * @param arrivalDate		到店日期
 * @param departureDate		离店日期
 */
function getPrepayDescription(prepayRules, nightlyRates, lastArrivalTime, roomNum, arrivalDate, departureDate) {

	var daytime1 = -1;
	var daytime2 = -1;
	var amount1 = 0.0;
	var amount2 = 0.0;
	// 0是金额，1是百分比
	var amountType1 = 0;
    var amountType2 = 0;
	var description = "";

	if (prepayRules == null || prepayRules.length < 1 || nightlyRates == null || nightlyRates.length < 1) {
		var result = {
			"daytime1": moment(arrivalDate).add(1, "hours").valueOf(),
			"daytime2": moment(arrivalDate).add(1, "hours").valueOf(),
			"amount1": amount1,
			"amount2": amount2,
			"amountType1": amountType1,
			"amountType2": amountType2,
			"description": "入住前可以随时取消。"
		};
	}

    var numOfDay = (departureDate-arrivalDate)/(3600*1000*24);
	var totalPrice = 0.0;
	for (var i = 0; i< nightlyRates.length; i++) {
		totalPrice += nightlyRates[i].member;
	}
    for (var i = 0; i < numOfDay; i++) {
        var thisDay = arrivalDate + (3600 * 1000 * 24 * i);
        for (var j = 0; j < prepayRules.length; j++) {
            var prepayRule = prepayRules[j];
            if (thisDay >= prepayRule.startDate && thisDay <= prepayRule.endDate) {
                // 星期不符合直接跳过
                var week = moment(thisDay).day();
                if (prepayRule.weekSet.indexOf(week) < 0) {
                    break;
                }
                if (daytime1 != 0 && daytime2 != 0) {
                    if (prepayRule.changeRule == "PrepayNoChange") {
                        daytime1 = 0;
                        daytime2 = 0;
                    } else if (prepayRule.changeRule == "PrepayNeedSomeDay") {
                        daytime1 = moment(arrivalDate).add(1, "days").add(-prepayRule.hour, "hours").valueOf();
                        daytime2 = moment(arrivalDate).add(1, "days").add(-prepayRule.hour2, "hours").valueOf();
                        if (prepayRule.deductFeesBefore == 1) {
                        	if (prepayRule.cashScaleFirstBefore == "FristNight") {
                        		if (amount1 == 0) {
                                    amountType1 = 0;
                                    amount1 = nightlyRates[0].member;
								} else {
                        			if (amountType1 == 1 && totalPrice * amount1 * 0.01 < nightlyRates[0].member) {
                                        amountType1 = 0;
                                        amount1 = nightlyRates[0].member;
									} else if (amountType1 == 0 && amount1 < nightlyRates[0].member) {
                                        amountType1 = 0;
                                        amount1 = nightlyRates[0].member;
									}
								}
							} else if (prepayRule.cashScaleFirstBefore == "Percent") {
                        		if (amount1 == 0) {
                                    amountType1 = 1;
                                    amount1 = prepayRule.deductNumBefore;
								} else {
                        			if (amountType1 == 1 && amount1  < prepayRule.deductNumBefore) {
                                        amount1 = prepayRule.deductNumBefore;
									} else if (amountType1 == 0 && amount1 < prepayRule.deductNumBefore * 0.01 * totalPrice) {
                                        amountType1 = 1;
                                        amount1 = prepayRule.deductNumBefore;
									}
								}
							} else if (prepayRule.cashScaleFirstBefore == "Money") {
                                if (amount1 == 0) {
                                    amountType1 = 0;
                                    amount1 = prepayRule.deductNumBefore;
                                } else {
                                    if (amountType1 == 1 && amount1 * 0.01 * totalPrice  < prepayRule.deductNumBefore) {
                                        amountType1 = 0;
                                        amount1 = prepayRule.deductNumBefore;
                                    } else if (amountType1 == 0 && amount1 < prepayRule.deductNumBefore) {
                                        amount1 = prepayRule.deductNumBefore;
                                    }
                                }
							}
						}
                        if (prepayRule.deductFeesAfter == 1) {
                            if (prepayRule.cashScaleFirstAfter == "FristNight") {
                                if (amount2 == 0) {
                                    amountType2 = 0;
                                    amount2 = nightlyRates[0].member;
                                } else {
                                    if (amountType2 == 1 && totalPrice * amount2 * 0.01 < nightlyRates[0].member) {
                                        amountType2 = 0;
                                        amount2 = nightlyRates[0].member;
                                    } else if (amountType2 == 0 && amount2 < nightlyRates[0].member) {
                                        amountType2 = 0;
                                        amount2 = nightlyRates[0].member;
                                    }
                                }
                            } else if (prepayRule.cashScaleFirstAfter == "Percent") {
                                if (amount2 == 0) {
                                    amountType2 = 1;
                                    amount2 = prepayRule.deductNumAfter;
                                } else {
                                    if (amountType2 == 1 && amount2 < prepayRule.deductNumAfter) {
                                        amount2 = prepayRule.deductNumBefore;
                                    } else if (amountType2 == 0 && amount2 < prepayRule.deductNumAfter * 0.01 * totalPrice) {
                                        amountType2 = 1;
                                        amount2 = prepayRule.deductNumBefore;
                                    }
                                }
                            } else if (prepayRule.cashScaleFirstAfter == "Money") {
                                if (amount2 == 0) {
                                    amountType2 = 0;
                                    amount2 = prepayRule.deductNumAfter;
                                } else {
                                    if (amountType2 == 1 && amount2 * 0.01 * totalPrice  < prepayRule.deductNumAfter) {
                                        amountType2 = 0;
                                        amount2 = prepayRule.deductNumAfter;
                                    } else if (amountType2 == 0 && amount2 < prepayRule.deductNumBefore) {
                                        amount2 = prepayRule.deductNumAfter;
                                    }
                                }
                            }
                        }
					} else if (prepayRule.changeRule == "PrepayNeedOneTime") {
                        daytime1 = moment(moment(prepayRule.dateNum).format("YYYY-MM-DD") + " " + prepayRule.time, "YYYY-MM-DD HH:mm").valueOf();
                        daytime2 = moment(arrivalDate).add(1, "days").valueOf();
					}
				}
            }
        }

        if (amount1 == 0 && amount2 == 0) {
        	if (moment(daytime2).valueOf() < moment().valueOf()) {
                description += "一经预订不可取消。";
			} else {
                description += "在" + moment(daytime2).format("YYYY年MM月DD日HH时") + "前可以免费取消，之后不可取消";
			}

		} else if (amount1 == 0 && amount2 != 0) {
        	if (moment(daytime1).valueOf() > moment().valueOf()) {
                description += "在" + moment(daytime1).format("YYYY年MM月DD日HH时") + "前可以免费取消，";
			}
			if (moment(daytime2).valueOf() < moment().valueOf()) {
                description += "一经预订不可取消。";
			} else {
                description += "在" + moment(daytime1).format("YYYY年MM月DD日HH时") + moment(daytime2).format("YYYY年MM月DD日HH时") + "之间取消将扣除" + (amountType2 == 0? amount2*roomNum +"元": "房费的"+amount2+"%") + "作为罚金，";
                description += "之后不可取消。";
			}
        } else if (amount1 != 0 && amount2 == 0) {
            if (moment(daytime1).valueOf() > moment().valueOf()) {
                description += "在" + moment(daytime1).format("YYYY年MM月DD日HH时") + "前取消将扣除" + (amountType1 == 0? amount1*roomNum +"元": "房费的"+amount1 + "%") + "作为罚金，";
            }
            if (moment(daytime2).valueOf() < moment().valueOf()) {
                description += "一经预订不可取消。";
            } else {
                description += "在" + moment(daytime1).format("YYYY年MM月DD日HH时") + moment(daytime2).format("YYYY年MM月DD日HH时") + "之间可以免费取消，";
                description += "之后不可取消。";
            }
		} else if(amount1 != 0 && amount2 != 0) {
            if (moment(daytime1).valueOf() > moment().valueOf()) {
                description = "在" + moment(daytime1).format("YYYY年MM月DD日HH时") + "前取消将扣除" + (amountType1 == 0? amount1*roomNum +"元": "房费的"+amount1 + "%") + "作为罚金，";
            }
            if (moment(daytime2).valueOf() < moment().valueOf()) {
                description += "一经预订不可取消。";
            } else {
                description += "在" + moment(daytime1).format("YYYY年MM月DD日HH时") + moment(daytime2).format("YYYY年MM月DD日HH时") + "之间取消将扣除"+ (amountType2 == 0? amount2*roomNum +"元": "房费的"+amount2+"%") + "作为罚金，";
                description += "之后不可取消。";
            }
		}

		var result = {
            "daytime1": daytime1,
            "daytime2": daytime2,
            "amount1": amount1,
            "amount2": amount2,
            "amountType1": amountType1,
            "amountType2": amountType2,
            "description": description
		};
        return result;
    }

}