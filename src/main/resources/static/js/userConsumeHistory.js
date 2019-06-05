$(document).ready(function () {
    var chargeList;
    var userid=sessionStorage.getItem("id")
    console.log(userid)
    getChargeList();
    getTicketList();
    function getChargeList(userid) {
        getRequest(
            '/vip/getChargeRecord'+userid,
            function (res) {
                renderChargeList(res.content);
            },
            function (error) {
                alert(error);
            }
        )
    }

    function renderChargeList(res) {
        for (var i=0;i<res.content.length;i++) {
            var chargeTime = res.chargeTime;
            var chargeAmount = res.chargeAmount;
            var cardBalance = res.cardBalance;
            var chargetex = "";

            chargetex+= "<tr><td>"+chargeTime+"</td>"+
                "<td>"+chargeAmount+"</td>"+
                "<td>"+cardBalance+"</td></tr>";
            $('#charge-list-body').append(chargetex);
        }
    }

    function getTicketList() {
        getRequest(
            '/user/member/getConsumption'+userid,
            function (res) {
                renderTicketList(res.content);
            }
        )
    }
    function renderTiceketList(ticketList) {
        for (var j = 0; j < ticketList.length; j++) {
            var movieName = ticketList[j].movieName;
            var startTime=ticketList[j].startTime;
            var endTime=ticketList[j].endTime;
            var bodyContent="";

            bodyContent+="<tr><td>"+movieName+"</td>"+
                "<td>"+startTime+"</td>"+
                "<td>"+endTime+"</td>"+
                "<button>"+"查看详情"+"</button>"

            $('ticket-list-body').append(bodyContent);
        }
    }
})