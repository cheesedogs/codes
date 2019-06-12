$(document).ready(function () {
    var chargeList;
    var ticketListForInfo;

    getChargeList();
    getTicketList();
    function getChargeList() {
        getRequest(
            '/vip/getChargeRecord?id='+parseInt(sessionStorage.getItem("id")),
            function (res) {
                chargeList=res.content;
                renderChargeList(res.content);
            },
            function (error) {
                alert(error);
            }
        )
    }

    function renderChargeList(chargeList) {
        for (var i=0;i<chargeList.length;i++) {
            var chargeTime = chargeList[i].time.split("T")[0]+"  "+chargeList[i].time.split("T")[1].split(".")[0];
            var chargeAmount = chargeList[i].amount;
            var cardBalance = chargeList[i].balance;
            var chargetex = "";

            chargetex+= "<tr><td>"+chargeTime+"</td>"+
                "<td>"+chargeAmount+"</td>"+
                "<td>"+cardBalance+"</td></tr>";
            $('#charge-list-body').append(chargetex);
        }
    }

    function getTicketList() {
        getRequest(
            '/user/member/getConsumption?id='+parseInt(sessionStorage.getItem("id")),
            function (res) {
                localStorage.setItem("ticketList",res.content);
                renderTicketList(res.content);
            }
        )
    }

    function renderTicketList(ticketList) {
        $('#ticket-list-body').empty();
        ticketListForInfo=ticketList;
        for (var j = 0; j < ticketList.length; j++) {
            var scheduleItem=ticketList[j].schedule;
            var movieName = scheduleItem.movieName;
            var startTime;
            startTime=scheduleItem.startTime.split("T")[0]+" "+scheduleItem.startTime.split("T")[1].substring(0,5);
            var endTime;
            endTime=scheduleItem.endTime.split("T")[0]+" "+scheduleItem.endTime.split("T")[1].substring(0,5);
            var bodyContent;

            bodyContent+="<tr><td>"+movieName+"</td>"+
                "<td>"+startTime+"</td>"+
                "<td>"+endTime+"</td>"+
                "<td><button class='btn-info' id=\""+ticketList[j].id+"\" >"+"查看详情"+"</button></td></tr>";

        }
        $('#ticket-list-body').append(bodyContent);
    }

    $(document).on('click','.btn-info',function (e) {
        var ticketid=e.target.id;
        for (var i = 0; i < ticketListForInfo.length; i++) {
            if ( ticketListForInfo[i].id==ticketid){
                console.log(ticketListForInfo[i]);
                var scheduleItem=ticketListForInfo[i].schedule;
                console.log(scheduleItem);
                var movieName=scheduleItem.movieName;
                var seat=ticketListForInfo[i].rowIndex+"排"+ticketListForInfo[i].columnIndex+"列";
                var fare=scheduleItem.fare;
                var startTime;
                startTime=scheduleItem.startTime.split("T")[0]+" "+scheduleItem.startTime.split("T")[1].substring(0,5);
                var endTime;
                endTime=scheduleItem.endTime.split("T")[0]+" "+scheduleItem.endTime.split("T")[1].substring(0,5);
                var hall=scheduleItem.hallName;

                var bodyContex;
                bodyContex+="<tr><td>"+movieName+"</td>"+
                    "<td>"+startTime+"</td>"+
                    "<td>"+endTime+"</td>"+
                    "<td>"+seat+"</td>"+
                    "<td>"+fare+"</td></tr>";

                $('#ticketInfoShow').empty();
                $('#ticketInfoShow').append(bodyContex);
                $('#ticketInfo').modal('show');
                break;
            }
        }
    })

    $(document).on('click','#confirmBtn',function (e) {
        $('#ticketInfo').modal('hide');
    })
})