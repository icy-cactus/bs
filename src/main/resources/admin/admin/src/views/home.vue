<template>
    <div class="content">
        <div class="text"
            :style='{ "margin": "50px auto", "fontSize": "24px", "color": "rgb(51, 51, 51)", "textAlign": "center", "fontWeight": "bold" }'>
            欢迎使用 {{ this.$project.projectName }}</div>
            <div v-if="this.$storage.get('role') === '管理员'" style="width: 100%">
            <div id="home-chart" style="height: 300px"></div>
        </div>
    </div>
    
</template>
<script>
//0
import router from '@/router/router-static'
import * as echarts from 'echarts'
export default {
    data() {
        return {
            charts: [],
            ydata: [],
            xdata: [],
            chartsdata: [],
            year: 2025,
        };
    },
    mounted() {
        this.init();
        this.getCharts();
        this.homeChart();
    },
    methods: {
        init() {
            if (this.$storage.get('Token')) {
                this.$http({
                    url: `${this.$storage.get('sessionTable')}/session`,
                    method: "get"
                }).then(({ data }) => {
                    if (data && data.code != 0) {
                        router.push({ name: 'login' })
                    }
                });
            } else {
                router.push({ name: 'login' })
            }
        },


        getCharts() {
            this.$http({
                url: "/ticketreserve/getCharts",
                method: "get",
                params: {
                    year: this.year,
                },
            }).then(({ data }) => {
                console.log(data);
                this.chartsdata = data.data;
                console.log(this.chartsdata);
                for (let key in this.chartsdata) {
                    this.xdata.push(key);
                    this.ydata.push(this.chartsdata[key]);
                    console.log(this.xdata);
                    console.log(this.ydata);
                }
                this.homeChart();
            });
        },
        extractMonth(dateString) {
            // 将日期字符串转换为 Date 对象
            const date = new Date(dateString);
            // 获取月份 (0-11，因此需要加1)
            const month = date.getMonth() + 1;
            // 返回格式化的月份 (如果需要二位数)
            return month < 10 ? `0${month}` : month.toString();
        },
        homeChart() {
            // 基于准备好的dom，初始化echarts实例
            var myChart = this.$echarts.init(document.getElementById("home-chart"));
            // 指定图表的配置项和数据
            var option = {
                title: {
                    text: "门票预约数", // 设置标题文本
                    left: "5%", // 可选值: 'center' | 'left' | 'right'
                    top: "10px", // 可选值: 像素值或百分比
                    textStyle: {
                        color: "#333", // 标题颜色
                        fontSize: 18, // 字体大小
                        fontWeight: "bold", // 字体粗细
                    },
                },
                xAxis: {
                    data: this.xdata,
                },
                grid: {},
                tooltip: {
                    trigger: "axis",
                },
                yAxis: {
                    type: "value",
                },
                series: [
                    {
                        name: "预约数",
                        type: "bar",
                        stack: "总量",
                        data: this.ydata,
                        barWidth: "30%",
                    },
                ],
            };


            // // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
            //根据窗口的大小变动图表
            window.onresize = function () {
                myChart.resize();
            };
        },
    }
};
</script>
<style lang="scss" scoped>
.cardView {
    display: flex;
    flex-wrap: wrap;
    width: 100%;

    .cards {
        display: flex;
        align-items: center;
        width: 100%;
        margin-bottom: 10px;
        justify-content: center;

        .card {
            width: calc(25% - 20px);
            margin: 0 10px;

            ::v-deep.el-card__body {
                padding: 0;
            }
        }
    }
}
</style>
