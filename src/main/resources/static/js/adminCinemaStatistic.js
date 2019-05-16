$(document).ready(function() {

    var statisticDate = formatDate(new Date());

    var days = 0;

    var movieNum = 0;

    changeDate();

    changeScaleAndCunt();

    getScheduleRate();

    getBoxOffice();

    getAudiencePrice();

    getPlacingRate(statisticDate);

    getPolularMovie(days, movieNum);

    function changeDate() {
        // 过滤条件变化后重新查询
        $('#statistic-date-input').change(function () {
            statisticDate = $('#statistic-date-input').val();
            getPlacingRate(statisticDate);
        });
    }

    function changeScaleAndCunt() {
        // 过滤条件变化后重新查询
        $('#days-set-input').change(function () {
            days = $('#days-set-input').val();
            getPlacingRate(days, movieNum);
        });
        $('#movienum-set-input').change(function () {
            movieNum = $('#movienum-set-input').val();
            getPlacingRate(days, movieNum);
        });
    }

    function getScheduleRate() {

        getRequest(
            '/statistics/scheduleRate',
            function (res) {
                var data = res.content||[];
                var tableData = data.map(function (item) {
                   return {
                       value: item.time,
                       name: item.name
                   };
                });
                var nameList = data.map(function (item) {
                    return item.name;
                });
                var option = {
                    title : {
                        text: '今日排片率',
                        subtext: new Date().toLocaleDateString(),
                        x:'center'
                    },
                    tooltip : {
                        trigger: 'item',
                        formatter: "{a} <br/>{b} : {c} ({d}%)"
                    },
                    legend: {
                        x : 'center',
                        y : 'bottom',
                        data:nameList
                    },
                    toolbox: {
                        show : true,
                        feature : {
                            mark : {show: true},
                            dataView : {show: true, readOnly: false},
                            magicType : {
                                show: true,
                                type: ['pie', 'funnel']
                            },
                            restore : {show: true},
                            saveAsImage : {show: true}
                        }
                    },
                    calculable : true,
                    series : [
                        {
                            name:'面积模式',
                            type:'pie',
                            radius : [30, 110],
                            center : ['50%', '50%'],
                            roseType : 'area',
                            data:tableData
                        }
                    ]
                };
                var scheduleRateChart = echarts.init($("#schedule-rate-container")[0]);
                scheduleRateChart.setOption(option);
            },
            function (error) {
                alert(JSON.stringify(error));
            }
        );
    }

    function getBoxOffice() {

        getRequest(
            '/statistics/boxOffice/total',
            function (res) {
                var data = res.content || [];
                var tableData = data.map(function (item) {
                    return item.boxOffice;
                });
                var nameList = data.map(function (item) {
                    return item.name;
                });
                var option = {
                    title : {
                        text: '所有电影票房',
                        subtext: '截止至'+new Date().toLocaleDateString(),
                        x:'center'
                    },
                    xAxis: {
                        type: 'category',
                        data: nameList
                    },
                    yAxis: {
                        type: 'value'
                    },
                    series: [{
                        data: tableData,
                        type: 'bar'
                    }]
                };
                var scheduleRateChart = echarts.init($("#box-office-container")[0]);
                scheduleRateChart.setOption(option);
            },
            function (error) {
                alert(JSON.stringify(error));
            });
    }

    function getAudiencePrice() {
        getRequest(
            '/statistics/audience/price',
            function (res) {
                var data = res.content || [];
                var tableData = data.map(function (item) {
                    return item.price;
                });
                var nameList = data.map(function (item) {
                    return formatDate(new Date(item.date));
                });
                var option = {
                    title : {
                        text: '每日客单价',
                        x:'center'
                    },
                    xAxis: {
                        type: 'category',
                        data: nameList
                    },
                    yAxis: {
                        type: 'value'
                    },
                    series: [{
                        data: tableData,
                        type: 'line'
                    }]
                };
                var scheduleRateChart = echarts.init($("#audience-price-container")[0]);
                scheduleRateChart.setOption(option);
            },
            function (error) {
                alert(JSON.stringify(error));
            });
    }

    function getPlacingRate(statisticDate) {
        // todo
        getRequest(
            '/statistics/PlacingRate?date=' + statisticDate.replace(/-/g, '/'),
            function (res) {
                var data = res.content || [];
                var tableData = data.map(function (item) {
                    return {
                        placeRate: item.placingRate
                    };
                });
                var placingRateList = data.map(function (item) {
                    return item.placingRate;
                })
                var option = {
                    title: {
                        text: '上座率',
                        subtext: new Date().toLocaleDateString(),
                        x: 'center'
                    },
                    tooltip: {
                        trigger: 'item',
                        formatter: "{a} <br/>{b} : {c} ({d}%)"
                    },
                    legend: {
                        x: 'center',
                        y: 'bottom',
                        data: placingRateList
                    },
                    toolbox: {
                        show: true,
                        feature: {
                            mark: {show: true},
                            dataView: {show: true, readOnly: false},
                            magicType: {
                                show: true,
                                type: ['pie', 'funnel']
                            },
                            restore: {show: true},
                            saveAsImage: {show: true}
                        }
                    },
                    calculable: true,
                    series: [{
                        name: '面积模式',
                        type: 'pie',
                        radius: [30, 110],
                        center: ['50%', '50%'],
                        roseType: 'area',
                        data: tableData
                    }]
                };
                var placingRateChart = echarts.init($("#place-rate-container")[0]);
                placingRateChart.setOption(option);
            },
            function (error) {
                alert(JSON.stringify(error));
            }
        );
    }

    function getPolularMovie(days, movieNum) {
        // todo
        getRequest(
            '/statistics/popular/movie?days=' + days + '&movieNum=' + movieNum,
            function (res) {
                var data = res.content || [];
                var tableData = data.map(function (item) {
                    return item.boxOffice;
                });
                var nameList = data.map(function (item) {
                    return item.name;
                });
                var option = {
                    title: {
                        text: '最受欢迎电影',
                        subtext: '最近' + days + '天，TOP ' + movieNum,
                        x: 'center'
                    },
                    xAxis: {
                        type: 'category',
                        data: nameList
                    },
                    yAxis: {
                        type: 'value'
                    },
                    series: [{
                        data: tableData,
                        type: 'bar'
                    }]
                };
                var scheduleRateChart = echarts.init($("#popular-movie-container")[0]);
                scheduleRateChart.setOption(option);
            },
            function (error) {
                alert(JSON.stringify(error));
            }
        )
    }

    $('#days-modify-btn').click(function () {
        $("#days-modify-btn").hide();
        $("#days-set-input").val(days);
        $("#days-set-input").show();
        $("#days-confirm-btn").show();
    });

    $('#days-confirm-btn').click(function () {
        var dayNum = $("#days-set-input").val();
        // 验证一下是否为数字
        postRequest(
            '/schedule/view/set',
            {day:dayNum},
            function (res) {
                if(res.success){
                    getPolularMovie(days, movieNum)
                    getCanSeeDayNum();
                    canSeeDate = dayNum;
                    $("#days-modify-btn").show();
                    $("#days-set-input").hide();
                    $("#days-confirm-btn").hide();
                } else{
                    alert(res.message);
                }
            },
            function (error) {
                alert(JSON.stringify(error));
            }
        );
    })
});