$(document).ready(function () {

    getStrategies();

    function getStrategies() {
        getRequest(
            '/vip/getVIPPromotion',
            function (res) {
                var strategies = res.content;
                renderStrategies(strategies);
            },
            function (error) {
                alert(JSON.stringify(error));
            }
        );
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
                                "<div class='money'>$" + strategy.minus + "</div>" +
                                "<div class='pay-line' style='white-space:nowrap;'>满" + strategy.standard + "元减</div>" +
                            "</div>" +
                        "</div>" +
                        "<div class='back'>" +
                            "<div class='tip' style='background-image: linear-gradient(60deg, #96deda 0%, #50c9c3 100%);'>" +
                                "<button class='button' onclick='changeStrategy(this)' data-backdrop='static' data-toggle='modal' data-target='#editStrategyModal'>修改</button>" +
                            "</div>" +
                        "</div>" +
                    "</div>" +
                "</div>";
        });

        $(".content-strategy").append(strategiesDomStr);
    }

    $("#strategy-form-btn").click(function () {
        var form = {
            standard: $("#coupon-target-input").val(),
            minus: $("#coupon-discount-input").val(),
            startTime: new Date($("#strategy-start-date-input").val()).toISOString().split('T')[0],
            endTime: new Date($("#strategy-end-date-input").val()).toISOString().split('T')[0]
        };
        postRequest(
            '/vip/releaseVIPPromotion',
            form,
            function (res) {
                if (res.success) {
                    getStrategies();
                    $("#strategyModal").modal('hide');
                } else {
                    console.log(res);
                    alert(res.message);
                }
            },
            function (error) {
                alert(JSON.stringify(error));
            }
        );
    });

    window.changeStrategy = function (strategy) {
        var strObj = strategy.parentNode.parentNode.parentNode.parentNode;
        var reg = /[\u4e00-\u9fa5]/g;
        var id = parseInt(strObj.getElementsByClassName('id').item(0).innerHTML.split(' ')[1]);
        var startTime = formatDate(new Date(strObj.getElementsByClassName('start_time').item(0).innerHTML.split(' ')[1]));
        var endTime = formatDate(new Date(strObj.getElementsByClassName('end_time').item(0).innerHTML.split(' ')[1]));
        var targetAmount = parseInt(strObj.getElementsByClassName('pay-line').item(0).innerHTML.replace(reg, ''));
        var discountAmount = parseInt(strObj.getElementsByClassName('money').item(0).innerHTML.slice(1));
        $("#edit-strategy-id-input").val(id);
        $("#edit-strategy-start-date-input").val(startTime);
        $("#edit-strategy-end-date-input").val(endTime);
        $("#edit-coupon-target-input").val(targetAmount);
        $("#edit-coupon-discount-input").val(discountAmount);
    };

    $("#edit-strategy-form-btn").click(function () {
        var form = {
            id: $("#edit-strategy-id-input").val(),
            standard: $("#edit-coupon-target-input").val(),
            minus: $("#edit-coupon-discount-input").val(),
            startTime: new Date($("#edit-strategy-start-date-input").val()).toISOString().split('T')[0],
            endTime: new Date($("#edit-strategy-end-date-input").val()).toISOString().split('T')[0]
        };

        postRequest(
            '/vip/updateVIPPromotion',
            form,
            function (res) {
                if (res.success) {
                    getStrategies();
                    $("#editStrategyModal").modal('hide');
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