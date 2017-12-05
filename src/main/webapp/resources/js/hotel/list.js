$(function() {
    $('#dateSelect span').html('入： ' + moment().format('YYYY-MM-DD') + ' - 离： ' + moment().add('days', 1).format('YYYY-MM-DD'));
    $('#dateSelect').daterangepicker();

    var cityPicker = new IIInsomniaCityPicker({
        data: cityData,
        target: '#cityChoice',
        valType: 'k-v',
        hideCityInput: '#city',
        hideProvinceInput: '#province',
        callback: function(city_id){
        }
    });

    cityPicker.init();

    $('div[name="divSelect"]').find('ul').find('li').bind('click', function(){
        $(this).parent().parent().find('button').html($(this).find('a').html() + '<span class="caret"></span>');
    });

    $('button[name="btnStarRate"]').click(function() {
        if($(this).attr("id") == "btnStarRateDefault"){
            if ($(this).attr("class").indexOf("btn btn-default") >= 0) {
                $('button[name="btnStarRate"]').each(function(i, info) {
                    if(info.getAttribute("id") != "btnStarRateDefault"){
                        info.setAttribute("class", "btn btn-default");
                    } else {
                        info.setAttribute("class", "btn btn-info");
                    }
                });
            }
        } else {
            if ($(this).attr("class").indexOf("btn btn-default") >= 0) {
                $(this).attr("class", "btn btn-info");
                $('button[name="btnStarRate"]').each(function(i, info) {
                    if(info.getAttribute("id") == "btnStarRateDefault"){
                        info.setAttribute("class", "btn btn-default");
                    }
                });
            } else {
                $(this).attr("class", "btn btn-default");
                var mark = 0;
                $('button[name="btnStarRate"]').each(function(i, info) {
                    if(info.getAttribute("id") != "btnStarRateDefault"
                        && (info.getAttribute("class").indexOf("btn btn-default") >= 0)){
                        mark++;
                    }
                });
                if (mark >= 4) {
                    $('#btnStarRateDefault').attr("class", "btn btn-info")
                }
            }
        }
    });

    $('#pullMoreHotel').click(function(){
        clickPullMoreHotel();
    });
});

function clickPullMoreHotel(){

    var hotelList = "";
    hotelList += '<div class="list-group-item"><div class="container my-hotellist">';
    hotelList += '<img src="http://www.runoob.com/try/bootstrap/layoutit/v3/default3.jpg" />';
    hotelList += '<span>如家酒店</span><span>4.5分 <span>5808条点评</span></span><span>经济型</span>';
    hotelList += '<span>天安门/王府井商业区</span><span>￥448</span></div></div>';
//	hotelList += '<div id="pullMoreHotel" style="text-align: center;">点击加载更多</div>';
//	$('#pullMoreHotel').remove();
    $('#divHotelList').append(hotelList);
}
