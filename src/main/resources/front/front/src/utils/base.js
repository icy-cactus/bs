const base = {
    get() {
        return {
            url : "http://localhost:8080/inttourismsys/",
            name: "inttourismsys",
            // 退出到首页链接
            indexUrl: 'http://localhost:8080/inttourismsys/front/dist/index.html'
        };
    },
    getProjectName(){
        return {
            projectName: "星际旅游信息网"
        } 
    }
}
export default base
