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
            projectName: "智能旅游推荐系统"
        } 
    }
}
export default base
