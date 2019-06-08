$(document).ready(function () {

    getStrategies();

    function getStrategies() {
        var strategies = [
            {
                'id': 1,
                'startTime': '2019-06-04 00:00:00',
                'endTime': '2019-06-23 00:00:00',
                'targetAmount': 20,
                'discountAmount': 10
            },
            {
                'id': 2,
                'startTime': '2019-06-04 00:00:00',
                'endTime': '2019-06-23 00:00:00',
                'targetAmount': 50,
                'discountAmount': 30
            }
        ];
        renderStrategies(strategies);
        /*getRequest(
            '/vip/getVIPPromotion',
            function (res) {
                var strategies = res.content;
                strategies = [
                    {
                        'startTime':'2019-06-04 00:00:00',
                        'endTime':'2019-06-23 00:00:00',
                        'targetAmount':20,
                        'discountAmount':10
                    }
                ];
                renderStrategies(strategies);
            },
            function (error) {
                alert(JSON.stringify(error));
            }
        );*/
    }

    function renderStrategies(strategies) {
        $(".content-strategy").empty();
        var strategiesDomStr = "";

        strategies.forEach(function (strategy) {
            strategiesDomStr +=
                "<div class='wrapper'>" +
                    "<div class='content'>" +
                        "<div class='coupon_title'>会员卡充值优惠</div>" +
                        "<div class='id' style='display:none;'>ID: " + strategy.id + "</div>" +
                        "<div class='start_time' style='display:none;'>生效日期: " + formatDate(new Date(strategy.startTime)) + "</div>" +
                        "<div class='end_time'>有效期至: " + formatDate(new Date(strategy.endTime)) + "</div>" +
                    "</div>" +
                    "<div class='split-line'></div>" +
                    "<div class='edit_container'>" +
                        "<div class='front'>" +
                            "<div class='tip'>" +
                                "<div class='money'>$" + strategy.discountAmount + "</div>" +
                                "<div class='pay-line'>满" + strategy.targetAmount + "元减</div>" +
                            "</div>" +
                        "</div>" +
                        "<div class='back'>" +
                            "<div class='tip' style='background-image: linear-gradient(60deg, #96deda 0%, #50c9c3 100%);'>" +
                                "<button class='button' data-target='#editStrategyModal'>修改</button>" +
                            "</div>" +
                        "</div>" +
                    "</div>" +
                "</div>";
        });

        $(".content-strategy").append(strategiesDomStr);
    }

    $("#strategy-form-btn").click(function () {
        var form = {
            targetAmount: $("#coupon-target-input").val(),
            discountAmount: $("#coupon-discount-input").val(),
            startTime: $("#strategy-start-date-input").val(),
            endTime: $("#strategy-end-date-input").val()
        };

        postRequest(
            '/vip/add',
            form,
            function (res) {
                if (res.success) {
                    getStrategies();
                    $("#strategyModal").modal('hide');
                } else {
                    alert(res.message);
                }
            },
            function (error) {
                alert(JSON.stringify(error));
            }
        );
    })

});

function changeStrategy(strategy) {
    var strObj = strategy.parentNode.parentNode.parentNode.parentNode;
    var reg = /[\u4e00-\u9fa5]/g;
    var id = parseInt(strObj.getElementsByClassName('id').item(0).innerHTML.split(' ')[1]);
    var startTime = formatDate(new Date(strObj.getElementsByClassName('start_time').item(0).innerHTML.split(' ')[1]));
    var endTime = formatDate(new Date(strObj.getElementsByClassName('end_time').item(0).innerHTML.split(' ')[1]));
    var discountAmount = parseInt(strObj.getElementsByClassName('money').item(0).innerHTML.slice(1));
    var targetAmount = parseInt(strObj.getElementsByClassName('pay-line').item(0).innerHTML.replace(reg, ''));
    console.log(strObj);
    console.log(id);
    console.log(startTime);
    console.log(endTime);
    console.log(discountAmount);
    console.log(targetAmount);


    $("#edit-strategy-form-btn").click(function () {
        var form = {
            id: $("#coupon-target-input").val(),
            targetAmount: $("#coupon-target-input").val(),
            discountAmount: $("#coupon-discount-input").val(),
            startTime: $("#strategy-start-date-input").val(),
            endTime: $("#strategy-end-date-input").val()
        };

        postRequest(
            '/vip/updateVIPPromotion',
            form,
            function (res) {
                if (res.success) {
                    getStrategies();
                    $("#strategyModal").modal('hide');
                } else {
                    alert(res.message);
                }
            },
            function (error) {
                alert(JSON.stringify(error));
            }
        );
    })
}