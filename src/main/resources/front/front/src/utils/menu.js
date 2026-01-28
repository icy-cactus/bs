const menu = {
  list() {
    return [
      {
        backMenu: [
          {
            child: [
              {
                appFrontIcon: "cuIcon-attentionfavor",
                buttons: ["新增", "查看", "修改", "删除"],
                menu: "用户",
                menuJump: "列表",
                tableName: "users",
              },
            ],
            menu: "用户管理",
          },
          // {
          //   child: [
          //     {
          //       appFrontIcon: "cuIcon-goodsnew",
          //       buttons: ["新增", "查看", "修改", "删除"],
          //       menu: "天气预报",
          //       menuJump: "列表",
          //       tableName: "weather",
          //     },
          //   ],
          //   menu: "天气预报管理",
          // },
          {
            child: [
              {
                appFrontIcon: "cuIcon-time",
                buttons: ["新增", "查看", "修改", "删除", "查看评论"],
                menu: "景点信息",
                menuJump: "列表",
                tableName: "scenicinfo",
              },
            ],
            menu: "景点信息管理",
          },
          {
            child: [
              {
                appFrontIcon: "cuIcon-newshot",
                buttons: ["查看", "删除"],
                menu: "门票预订",
                menuJump: "列表",
                tableName: "ticketreserve",
              },
            ],
            menu: "门票预订管理",
          },
          {
            child: [
              {
                appFrontIcon: "cuIcon-camera",
                buttons: ["新增", "查看", "修改", "删除", "查看评论"],
                menu: "路线",
                menuJump: "列表",
                tableName: "trafficroute",
              },
            ],
            menu: "路线管理",
          },
          {
            child: [
              {
                appFrontIcon: "cuIcon-phone",
                buttons: ["查看", "新增","修改","删除", "查看评论"],
                menu: "旅游攻略",
                menuJump: "列表",
                tableName: "travelguide",
              },
            ],
            menu: "旅游攻略管理",
          },
          {
            child: [
              {
                appFrontIcon: "cuIcon-time",
                buttons: ["查看", "修改", "删除"],
                menu: "轮播图管理",
                tableName: "config",
              },
              {
                appFrontIcon: "cuIcon-news",
                buttons: ["新增", "查看", "修改", "删除"],
                menu: "公告信息",
                tableName: "news",
              },
            ],
            menu: "系统管理",
          },
        ],
        frontMenu: [
          {
            child: [
              {
                appFrontIcon: "cuIcon-shop",
                buttons: ["查看", "订门票"],
                menu: "景点信息列表",
                menuJump: "列表",
                tableName: "scenicinfo",
              },
            ],
            menu: "景点信息模块",
          },
          {
            child: [
              {
                appFrontIcon: "cuIcon-addressbook",
                buttons: ["查看"],
                menu: "路线列表",
                menuJump: "列表",
                tableName: "trafficroute",
              },
            ],
            menu: "路线模块",
          },
          {
            child: [
              {
                appFrontIcon: "cuIcon-newshot",
                buttons: ["查看"],
                menu: "旅游攻略列表",
                menuJump: "列表",
                tableName: "travelguide",
              },
            ],
            menu: "旅游攻略模块",
          },
        ],
        hasBackLogin: "是",
        hasBackRegister: "否",
        hasFrontLogin: "否",
        hasFrontRegister: "否",
        roleName: "管理员",
        tableName: "admin",
      },
      {
        backMenu: [
          {
            child: [
              {
                appFrontIcon: "cuIcon-newshot",
                buttons: ["查看", "支付"],
                menu: "门票预订",
                menuJump: "列表",
                tableName: "ticketreserve",
              },
            ],
            menu: "门票预订管理",
          },
          {
            child: [
              {
                appFrontIcon: "cuIcon-phone",
                buttons: ["新增", "查看", "修改", "删除", "查看评论"],
                menu: "旅游攻略",
                menuJump: "列表",
                tableName: "travelguide",
              },
            ],
            menu: "旅游攻略管理",
          },
          {
            child: [
              {
                appFrontIcon: "cuIcon-addressbook",
                buttons: ["新增", "查看", "修改", "删除"],
                menu: "旅游规划",
                menuJump: "列表",
                tableName: "tourplan",
              },
            ],
            menu: "旅游规划管理",
          },
        ],
        frontMenu: [
          {
            child: [
              {
                appFrontIcon: "cuIcon-shop",
                buttons: ["查看", "订门票"],
                menu: "景点信息列表",
                menuJump: "列表",
                tableName: "scenicinfo",
              },
            ],
            menu: "景点信息模块",
          },
          {
            child: [
              {
                appFrontIcon: "cuIcon-addressbook",
                buttons: ["查看"],
                menu: "路线列表",
                menuJump: "列表",
                tableName: "trafficroute",
              },
            ],
            menu: "旅游路线",
          },
          {
            child: [
              {
                appFrontIcon: "cuIcon-newshot",
                buttons: ["查看"],
                menu: "旅游攻略列表",
                menuJump: "列表",
                tableName: "travelguide",
              },
            ],
            menu: "旅游攻略模块",
          },
        ],
        hasBackLogin: "是",
        hasBackRegister: "否",
        hasFrontLogin: "是",
        hasFrontRegister: "是",
        roleName: "用户",
        tableName: "users",
      },
    ];
  },
};
export default menu;
