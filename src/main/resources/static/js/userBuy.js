$(document).ready(function () {
    getMovieList();

    function getSchedule(scheduleid) {
        getRequest(
            '/schedule/'+ scheduleid,
            function (res) {
                return res;
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
                console.log(res.content);
                // renderTicketList(res.content);
            },
            function (error) {
                alert(error);
            });
    }

    // TODO:填空
    // function renderTicketList(list) {
    //     if (list.length== 0) {
    //         $('#date-none-hint').css("display", "");
    //     } else {
    //         $('#date-none-hint').css("display", "none");
    //     }
    //     var bodyContent="";
    //
    //     for (var i=0;i<list.length;i++){
    //         var scheduleItems=getSchedule(list[i].scheduleId);
    //         bodyContent += "<tr><td>" + scheduleItems.movieName + "</td>" +
    //             "<td>" + scheduleItems.hallName + "</td>" +
    //             "<td>" + list[i].columnIndex+"排"+list[i].rowIndex+"列"+ "</td>" +
    //             "<td>"+scheduleItems.startTime+"</td>" +
    //             "<td>"+scheduleItems.endTime+"</tr>"+
    //             "<td>"+list[i].state+"<td>";
    //     }
    //     $('#ticket-list-body').html(bodyContent);
    // }

});