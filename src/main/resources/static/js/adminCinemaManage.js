$(document).ready(function() {

    var canSeeDate = 0;

    getCanSeeDayNum();
    getCinemaHalls();

    function getCinemaHalls() {
        var halls = [];
        getRequest(
            '/hall/all',
            function (res) {
                halls = res.content;
                renderHall(halls);
            },
            function (error) {
                alert(JSON.stringify(error));
            }
        );
    }

    function renderHall(halls){
        $('#hall-card').empty();
        var hallDomStr = "";
        var hid=1;
        halls.forEach(function (hall) {
            var seat = "";
            for(var i =0;i<hall.row;i++){
                var temp = ""
                for(var j =0;j<hall.column;j++){
                    temp+="<div class='cinema-hall-seat'></div>";
                }
                seat+= "<div>"+temp+"</div>";
            }
            var hallDom =
                "<div class='cinema-hall'>" +
                "<div>" +
                "<span class='cinema-hall-name'>"+ hall.name +"</span>" +
                "<span class='cinema-hall-size'>"+ hall.column +'*'+ hall.row +"</span>" +"<span>"+"<button  type=\"button\"  class=\"btn btn-primary editHall\" data-backdrop=\"static\" data-toggle=\"modal\"  >修改 "+hall.name+" 影厅信息</button>"+"</span>"+
                "</div>" +
                "<div class='cinema-seat'>" + seat +
                "</div>" +
                "</div>";
            hallDomStr+=hallDom;
            localStorage.setItem("hallid"+hid,hid)
            hid+=1;
        });
        $('#hall-card').append(hallDomStr);
    }

    function getCanSeeDayNum() {
        getRequest(
            '/schedule/view',
            function (res) {
                canSeeDate = res.content;
                $("#can-see-num").text(canSeeDate);
            },
            function (error) {
                alert(JSON.stringify(error));
            }
        );
    }

    $('#canview-modify-btn').click(function () {
       $("#canview-modify-btn").hide();
       $("#canview-set-input").val(canSeeDate);
       $("#canview-set-input").show();
       $("#canview-confirm-btn").show();
    });

    $('#canview-confirm-btn').click(function () {
        var dayNum = $("#canview-set-input").val();
        // 验证一下是否为数字
        postRequest(
            '/schedule/view/set',
            {day:dayNum},
            function (res) {
                if(res.success){
                    getCanSeeDayNum();
                    canSeeDate = dayNum;
                    $("#canview-modify-btn").show();
                    $("#canview-set-input").hide();
                    $("#canview-confirm-btn").hide();
                } else{
                    alert(res.message);
                }
            },
            function (error) {
                alert(JSON.stringify(error));
            }
        );
    })

    $(document).on('click','.editHall',function (e) {
        $('#halleditModal').modal('show');
        var form=getHallEditForm();
        console.log(form);
    })

    $('#hall-form-btn').click(function () {
        var form=getHallForm();
        console.log(form);
        if(!validateHallForm(form)) {
            return;
        }
        postRequest(
            '/hall/addHall',
            form,
            function (res) {
                getCinemaHalls();
                $('#hallModal').model('hide');
            },
            function (error) {
                alert(error);
                $('#hallModal').model('hide');
            }
        )
    })

    $('#hall-edit-form-btn').click(function () {
        var form=getHallEditForm();
        console.log(form);
        if(!validateHallForm(form)){
            return;
        }
        postRequest(
            '/hall/updateHall',
            form,
            function (res) {
                $('#halleditModal').modal('hide');
                getCinemaHalls();
            },
            function (error) {
                alert(error);
                $('#halleditModal').modal('hide');
            }
        )
    })

    function getHallForm() {
        return  {
            hallName : $('#hall-name-input').val(),
            row : $('#hall-row-input').val(),
            col : $('#hall-col-input').val()
        }
    }

    function getHallEditForm() {
        return  {
            // id:
            hallName : $('#hall-edit-name-input').val(),
            row : $('#hall-edit-row-input').val(),
            col : $('#hall-edit-col-input').val()
        }
    }

    function validateHallForm(data) {
        var isValidate=true;
        if (!data.hallName){
            isValidate = false;
            $('#hall-name-input').parent('.form-group').addClass('has-error');
        }
        if (!data.row){
            isValidate = false;
            $('#hall-row-input').parent('.form-group').addClass('has-error');
        }
        if (!data.col){
            isValidate = false;
            $('#hall-col-input').parent('.form-group').addClass('has-error');
        }
        return isValidate;
    }
});