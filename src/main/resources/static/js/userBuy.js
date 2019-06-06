$(document).ready(function () {
    var ticketList;
    var refundStrategy;
    getMovieList();
    getRefundStrategy();

    //获取退票策略
    function getRefundStrategy(){
        getRequest(
            '/ticket/getRefundStrategy',
            function (res) {
                refundStrategy=res.content;
            }
        )
    }

    function getSchedule(scheduleid,i) {
        getRequest(
            '/schedule/'+ scheduleid,
            function (res) {
                renderTicketList(res.content,i);
            },
            function (error) {
                alert(error) ;
            }
        )
    }


    function getMovieList() {
        getRequest(
            '/ticket/get/' + sessionStorage.getItem('id'),
            function (res) {
                // console.log(res.content);
                ticketList = res.content;
                for(var i=0;i<res.content.length;i++){
                    getSchedule(ticketList[i].scheduleId,i);
                };
            },
            function (error) {
                alert(error);
            });
    }

    // TODO:填空

    function renderTicketList(schedule,i) {
        var bodyContent="";
        var stateti="";
        if (ticketList[i].state==1){
            stateti="已完成";
        }
        else {
            stateti="已失效";
        }
        var tiStartTime=schedule.startTime.toString();
        var smonth=tiStartTime.split("-")[1];
        var sday=tiStartTime.split("-")[2].substring(0,2);
        var sHourMin= tiStartTime.split("-")[2].substring(3,8)
        tiStartTime=smonth+"月"+sday+"日 "+sHourMin;

        var tiEndTime=schedule.endTime.toString();
        var emonth=tiEndTime.split("-")[1];
        var eday=tiEndTime.split("-")[2].substring(0,2);
        var eHourMin= tiEndTime.split("-")[2].substring(3,8)
        tiEndTime=emonth+"月"+eday+"日 "+eHourMin;

        bodyContent += "<tr><td>" + schedule.movieName + "</td>" +
            "<td>" + schedule.hallName  + "</td>" +
            "<td>" + (ticketList[i].rowIndex+1) + "排" + (ticketList[i].columnIndex+1) + "列" + "</td>" +
            "<td>" + tiStartTime + "</td>" +
            "<td>" + tiEndTime + "</td>" +
            "<td>" + stateti + "</td>";

        if (stateti=="已完成"){
            bodyContent+="<td><button id=\""+ticketList[i].id+"\" class='btn btn-primary refundBtn'>退票</button></td>"+"</tr>";
        }
        else {
            bodyContent+="<td></td></tr>"
        }
        $('#ticket-list-body').append(bodyContent);
    }

    $(document).on('click','.refundBtn',function (e) {
        console.log("-----下面是事件元素-----");
        console.log(e.target);
        console.log("-----下面是id元素-----");
        console.log(e.target.id);
        var ticketId=e.target.id;
        var r=confirm(refundStrategy+"开场前15分钟不得退票；开场前30分钟退票扣除60%费用");
        if (r){
            postRequest(
                '/ticket/refund',
                ticketId,
                function (res) {
                    alert("根据退票策略，您得到的退款为："+res.content+"50元");
                }
            )
        }

    })

});
//问题汇总：1：如何绑定id与影厅form，点击连接字中的btn后一起传送     2：与第一个问题相似，如何将id与影票相绑定，退票时点击连接字中的btn后一起传送