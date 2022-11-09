export default {
    // 设置为私有命名空间
    namespaced:true,
    state:{
        /*theme:{
            blue:"#101f30",
            gley:"#2b333e",
            red :"#7c1823",
            black:"#000000",
            green:"#15231b"
        }*/
        theme:[
            {
                name: "白色主题",
                value: "#FFFFFF",
                color: '#000'
            },
            {
                name:"蓝色主题",
                value:"#101f30",
                color:"#fff"
            },
            {
                name:"灰色主题",
                value:"#2b333e",
                color:"#fff"
            },
            {
                name:"黑色主题",
                value:"#000000",
                color:"#fff"
            },
            {
                name:"绿色主题",
                value:"#314a43",
                color:"#fff"
            }
        ]
    }
}