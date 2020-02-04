var My={
  // baseUrl:"http://192.168.42.245:8088/",
  baseUrl:"http://47.107.71.52:8088/",
  printInfo:function(info){
    console.log(JSON.stringify(info))
  },
  cancle:function(){
    api.closeFrame();
  },
  //打开指定url的frame，只含h eader
  openFrameHeader:function(url){
    var headerH = api.getGlobalData({
      key: 'headerH'
    });
    api.openFrame({
        url: url,
        bounces: false,
        reload: true,
        rect: { // 推荐使用Margin布局，用于适配屏幕的动态变化
            marginTop: headerH, // main页面距离win顶部的高度
            w: 'auto' // main页面的宽度 自适应屏幕宽度
        }
    })
  },
  openFrameFull:function(url,id){

    var headerH = api.getGlobalData({
      key: 'headerH'
    });
    var footerH = api.getGlobalData({
      key: 'footerH'
    });
    api.openFrame({
        url: url,
        bounces: false,
        reload: true,
        pageParam: {
            id: id
        },
        rect: { // 推荐使用Margin布局，用于适配屏幕的动态变化
            marginTop: headerH, // main页面距离win顶部的高度
            marginBottom: footerH,
            w: 'auto' // main页面的宽度 自适应屏幕宽度
        }
    })
  },

  // importJs:function(){
  //   document.write("<script src='./a.js'></script>");
  //   document.write("<script src='./a.js'></script>");
  //   document.write("<script src='./a.js'></script>");
  // }
}
