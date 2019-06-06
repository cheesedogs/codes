$(document).ready(function () {
    var ticketList;
    getMovieList();

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
            "<td>" + stateti + "</td>"+
            "<td><button class='btn btn-primary'>退票</button></td>"+"</tr>";
        $('#ticket-list-body').append(bodyContent);

    }

});