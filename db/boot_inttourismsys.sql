/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80036
 Source Host           : localhost:3306
 Source Schema         : boot_inttourismsys

 Target Server Type    : MySQL
 Target Server Version : 80036
 File Encoding         : 65001

 Date: 23/12/2025 16:49:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `role` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '管理员' COMMENT '角色',
  `addtime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '新增时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'admin', 'admin', '管理员', '2025-12-09 18:32:16');

-- ----------------------------
-- Table structure for chat
-- ----------------------------
DROP TABLE IF EXISTS `chat`;
CREATE TABLE `chat`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `userid` bigint(0) NOT NULL COMMENT '用户id',
  `adminid` bigint(0) NULL DEFAULT NULL COMMENT '管理员id',
  `ask` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '提问',
  `reply` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '回复',
  `isreply` int(0) NULL DEFAULT NULL COMMENT '是否回复',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '在线客服' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of chat
-- ----------------------------

-- ----------------------------
-- Table structure for config
-- ----------------------------
DROP TABLE IF EXISTS `config`;
CREATE TABLE `config`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '配置参数名称',
  `value` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '配置参数值',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '配置项备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '配置文件' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of config
-- ----------------------------
INSERT INTO `config` VALUES (1, 'picture1', 'upload/picture1.jpg', '首页轮播图1');
INSERT INTO `config` VALUES (2, 'picture2', 'upload/picture2.jpg', '首页轮播图2');
INSERT INTO `config` VALUES (3, 'picture3', 'upload/picture3.jpg', '首页轮播图3');

-- ----------------------------
-- Table structure for dictionary
-- ----------------------------
DROP TABLE IF EXISTS `dictionary`;
CREATE TABLE `dictionary`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dic_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字段',
  `dic_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字段名',
  `code_index` int(0) NULL DEFAULT NULL COMMENT '编码',
  `index_name` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '编码名字',
  `super_id` int(0) NULL DEFAULT NULL COMMENT '父字段id',
  `beizhu` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '字典' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of dictionary
-- ----------------------------
INSERT INTO `dictionary` VALUES (1, 'traveltype', '旅游类型', 1, '山水风光', NULL, NULL, NULL);
INSERT INTO `dictionary` VALUES (2, 'traveltype', '旅游类型', 2, '民族风情', NULL, NULL, NULL);
INSERT INTO `dictionary` VALUES (3, 'traveltype', '旅游类型', 3, '滨海度假', NULL, NULL, NULL);
INSERT INTO `dictionary` VALUES (4, 'traveltype', '旅游类型', 4, '长寿养生', NULL, NULL, NULL);
INSERT INTO `dictionary` VALUES (5, 'traveltype', '旅游类型', 5, '边关风情', NULL, NULL, NULL);
INSERT INTO `dictionary` VALUES (6, 'roomtype', '房间类型', 1, '山水景观房', NULL, NULL, NULL);
INSERT INTO `dictionary` VALUES (7, 'roomtype', '房间类型', 2, '民族特色房', NULL, NULL, NULL);
INSERT INTO `dictionary` VALUES (8, 'roomtype', '房间类型', 3, '滨海海景房', NULL, NULL, NULL);
INSERT INTO `dictionary` VALUES (9, 'roomtype', '房间类型', 4, '长寿养生房', NULL, NULL, NULL);
INSERT INTO `dictionary` VALUES (10, 'roomtype', '房间类型', 5, '商务舒适房', NULL, NULL, NULL);
INSERT INTO `dictionary` VALUES (11, 'hoteltype', '酒店类型', 1, '五星级酒店', NULL, NULL, NULL);
INSERT INTO `dictionary` VALUES (12, 'hoteltype', '酒店类型', 2, '四星级酒店', NULL, NULL, NULL);
INSERT INTO `dictionary` VALUES (13, 'hoteltype', '酒店类型', 3, '精品民宿', NULL, NULL, NULL);
INSERT INTO `dictionary` VALUES (14, 'hoteltype', '酒店类型', 4, '民族特色民宿', NULL, NULL, NULL);
INSERT INTO `dictionary` VALUES (15, 'hoteltype', '酒店类型', 5, '滨海度假酒店', NULL, NULL, NULL);
INSERT INTO `dictionary` VALUES (16, 'traveltype', '旅游类型', 6, '红色旅游', NULL, NULL, NULL);
INSERT INTO `dictionary` VALUES (17, 'traveltype', '旅游类型', 7, '美食体验', NULL, NULL, NULL);
INSERT INTO `dictionary` VALUES (18, 'traveltype', '旅游类型', 8, '自驾旅游', NULL, NULL, NULL);
INSERT INTO `dictionary` VALUES (19, 'traveltype', '旅游类型', 9, '亲子游乐', NULL, NULL, NULL);
INSERT INTO `dictionary` VALUES (20, 'traveltype', '旅游类型', 10, '文化体验', NULL, NULL, NULL);
INSERT INTO `dictionary` VALUES (21, 'traffictype', '路线类型', 1, '公交专线', NULL, NULL, NULL);
INSERT INTO `dictionary` VALUES (22, 'traffictype', '路线类型', 2, '景区观光车', NULL, NULL, NULL);
INSERT INTO `dictionary` VALUES (23, 'traffictype', '路线类型', 3, '城际旅游专线', NULL, NULL, NULL);
INSERT INTO `dictionary` VALUES (24, 'traffictype', '路线类型', 4, '景区直通车', NULL, NULL, NULL);
INSERT INTO `dictionary` VALUES (25, 'traffictype', '路线类型', 5, '自驾路线', NULL, NULL, NULL);
INSERT INTO `dictionary` VALUES (26, 'hoteltype', '酒店类型', 6, '商务酒店', NULL, NULL, NULL);
INSERT INTO `dictionary` VALUES (30, 'traffictype', '路线类型', 6, '景区公交', NULL, NULL, NULL);
INSERT INTO `dictionary` VALUES (31, 'traffictype', '路线类型', 7, '自驾', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for discussscenicinfo
-- ----------------------------
DROP TABLE IF EXISTS `discussscenicinfo`;
CREATE TABLE `discussscenicinfo`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `refid` bigint(0) NOT NULL COMMENT '关联表id',
  `userid` bigint(0) NOT NULL COMMENT '用户id',
  `avatarurl` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '头像',
  `nickname` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `content` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '评论内容',
  `reply` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '回复内容',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_discussscenicinfo_refid`(`refid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1763546085979 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '景点信息评论表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of discussscenicinfo
-- ----------------------------
INSERT INTO `discussscenicinfo` VALUES (101, '2025-12-10 02:32:15', 31, 11, 'upload/users1.jpg', '账号1', '桂林漓江竹筏漂流太震撼了！杨堤到兴坪段的九马画山栩栩如生，20元人民币背景图打卡必去。', '漓江的山水，永远是桂林的名片！');
INSERT INTO `discussscenicinfo` VALUES (102, '2025-12-10 02:32:15', 32, 12, 'upload/users2.jpg', '账号2', '阳朔西街夜景超美，石板路两旁的小店各具特色，酒吧的驻唱歌手水平很高，很有氛围感。', '阳朔西街，越夜越精彩！');
INSERT INTO `discussscenicinfo` VALUES (103, '2025-12-10 02:32:15', 33, 13, 'upload/users3.jpg', '账号3', '龙脊梯田金坑大寨太壮观了！十月中旬的稻谷金黄一片，缆车上去省时省力，瑶族阿姨的刺绣很精美。', '龙脊梯田的四季，各有各的美！');
INSERT INTO `discussscenicinfo` VALUES (104, '2025-12-10 02:32:15', 34, 14, 'upload/users4.jpg', '账号4', '北海银滩的沙子真的像面粉一样细，海水清澈，租个遮阳伞躺一下午太舒服了，日落绝美。', '北海银滩，天下第一滩名不虚传！');
INSERT INTO `discussscenicinfo` VALUES (105, '2025-12-10 02:32:15', 35, 15, 'upload/users5.jpg', '账号5', '德天跨国瀑布比想象中壮观！坐船近距离感受瀑布的震撼，中越边境的风情很特别，买的越南酸奶好喝。', '德天瀑布，中越边境的璀璨明珠！');
INSERT INTO `discussscenicinfo` VALUES (106, '2025-12-10 02:32:15', 36, 16, 'upload/users6.jpg', '账号6', '涠洲岛火山口地质公园的火山岩地貌很独特，鳄鱼山景区的海景超棒，滴水丹屏的日落一定要看。', '涠洲岛，中国最年轻的火山岛！');
INSERT INTO `discussscenicinfo` VALUES (107, '2025-12-10 02:32:15', 37, 17, 'upload/users7.jpg', '账号7', '南宁青秀山空气超好，龙象塔上能俯瞰南宁全景，兰园的兰花品种超多，春天去最合适。', '青秀山，南宁的城市绿肺！');
INSERT INTO `discussscenicinfo` VALUES (108, '2025-12-10 02:32:15', 38, 18, 'upload/users8.jpg', '账号8', '三江程阳八寨的风雨桥太精致了！侗族大歌表演很震撼，百家宴的长桌菜很有特色，糯米酒香甜。', '程阳八寨，侗族文化的活化石！');
INSERT INTO `discussscenicinfo` VALUES (109, '2025-12-11 14:30:20', 39, 19, 'upload/users9.jpg', '账号9', '桂林象鼻山景区不大但很有特色，象山水月的景观名不虚传，公园里的桂花糕很好吃。', '象鼻山，桂林的城徽！');
INSERT INTO `discussscenicinfo` VALUES (1763546085978, '2025-11-19 17:54:45', 31, 1678358276062, 'upload/1678358274781.jpg', '1', '漓江竹筏太值了，九马画山数出了7匹马！', NULL);

-- ----------------------------
-- Table structure for discusstrafficroute
-- ----------------------------
DROP TABLE IF EXISTS `discusstrafficroute`;
CREATE TABLE `discusstrafficroute`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `refid` bigint(0) NOT NULL COMMENT '关联表id',
  `userid` bigint(0) NOT NULL COMMENT '用户id',
  `avatarurl` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '头像',
  `nickname` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `content` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '评论内容',
  `reply` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '回复内容',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_discusstrafficroute_refid`(`refid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1739098555195 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '交通路线评论表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of discusstrafficroute
-- ----------------------------
INSERT INTO `discusstrafficroute` VALUES (101, '2025-12-10 02:32:15', 91, 11, 'upload/users1.jpg', '账号1', '南宁东站到桂林北站的高铁只要2小时，二等座169元，车上充电口很方便，准点率超高。', '广西高铁网络越来越便捷啦！');
INSERT INTO `discusstrafficroute` VALUES (102, '2025-12-10 02:32:15', 92, 12, 'upload/users2.jpg', '账号2', '桂林磨盘山码头到阳朔的漓江游船4小时，船上有自助餐，一路看山水太惬意了。', '漓江游船，最美的水上风景线！');
INSERT INTO `discusstrafficroute` VALUES (103, '2025-12-10 02:33:15', 93, 13, 'upload/users3.jpg', '账号3', '北海国际客运港到涠洲岛的客轮1.5小时，选北游25号船稳不颠簸，提前买票很重要。', '涠洲岛航线，安全又舒适！');
INSERT INTO `discusstrafficroute` VALUES (104, '2025-12-10 02:34:15', 94, 14, 'upload/users4.jpg', '账号4', '南宁琅东站到崇左德天瀑布的旅游专线3小时，车上有导游讲解，中途会停明仕田园。', '德天瀑布专线，一站式直达！');
INSERT INTO `discusstrafficroute` VALUES (105, '2025-12-10 02:35:15', 95, 15, 'upload/users5.jpg', '账号5', '柳州到三江的动车1.5小时，二等座58元，高铁站有到程阳八寨的大巴，很方便。', '三江动车，拉近侗族风情的距离！');
INSERT INTO `discusstrafficroute` VALUES (106, '2025-12-10 02:36:15', 96, 16, 'upload/users6.jpg', '账号6', '桂林两江机场到市区的大巴20元，40分钟到民航大厦，打车的话约80元，建议坐大巴划算。', '桂林机场交通，选择多样！');
INSERT INTO `discusstrafficroute` VALUES (107, '2025-12-10 02:37:15', 97, 17, 'upload/users7.jpg', '账号7', '阳朔汽车南站到龙脊梯田的直通车3小时，每天两班，比转车方便多了，座位舒服。', '阳朔到龙脊，直达更省心！');
INSERT INTO `discusstrafficroute` VALUES (108, '2025-12-10 02:38:15', 98, 18, 'upload/users8.jpg', '账号8', '北海银滩到老街的3路公交2元，40分钟直达，沿途能看海景，比打车更有感觉。', '北海公交，慢游城市的选择！');

-- ----------------------------
-- Table structure for discusstravelguide
-- ----------------------------
DROP TABLE IF EXISTS `discusstravelguide`;
CREATE TABLE `discusstravelguide`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `refid` bigint(0) NOT NULL COMMENT '关联表id',
  `userid` bigint(0) NOT NULL COMMENT '用户id',
  `avatarurl` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '头像',
  `nickname` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `content` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '评论内容',
  `reply` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '回复内容',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_discusstravelguide_refid`(`refid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1759911597227 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '旅游攻略评论表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of discusstravelguide
-- ----------------------------
INSERT INTO `discusstravelguide` VALUES (101, '2025-12-10 02:32:15', 101, 11, 'upload/users1.jpg', '账号1', '桂林阳朔3日游攻略太实用了！按攻略订的漓江竹筏早班车避开了人流，遇龙河漂流选的人工筏更有感觉。', '很高兴攻略能帮到您，桂林欢迎常来！');
INSERT INTO `discusstravelguide` VALUES (102, '2025-12-10 02:32:15', 102, 12, 'upload/users2.jpg', '账号2', '北海涠洲岛2日游攻略里的住宿推荐超棒，住在滴水丹屏附近看日落太方便，租电动车环岛的建议很实用。', '涠洲岛的美，需要慢慢发现！');
INSERT INTO `discusstravelguide` VALUES (103, '2025-12-10 02:33:15', 103, 13, 'upload/users3.jpg', '账号3', '南宁崇左德天瀑布1日游攻略的时间规划很合理，早上7点出发避开了团队游客，拍照超爽。', '德天瀑布的美景，值得早起！');
INSERT INTO `discusstravelguide` VALUES (104, '2025-12-10 02:34:15', 104, 14, 'upload/users4.jpg', '账号4', '广西桂柳北环线5日游攻略太全面了！从桂林到柳州再到北海，每个城市的特色都涵盖了，租车自驾很方便。', '广西环线，一次看遍不同风景！');
INSERT INTO `discusstravelguide` VALUES (105, '2025-12-10 02:35:15', 105, 15, 'upload/users5.jpg', '账号5', '龙脊梯田秋收季旅游攻略的最佳拍摄点推荐太准了！金佛顶的全景真的无敌，建议带长焦镜头。', '龙脊的秋天，是金色的童话！');
INSERT INTO `discusstravelguide` VALUES (106, '2025-12-10 02:36:15', 106, 16, 'upload/users6.jpg', '账号6', '涠洲岛潜水攻略里推荐的蓝湾潜水点超棒，能见度10米，看到了珊瑚和热带鱼，教练很专业。', '涠洲岛潜水，探索海底世界！');
INSERT INTO `discusstravelguide` VALUES (107, '2025-12-10 02:37:15', 107, 17, 'upload/users7.jpg', '账号7', '桂林山水甲天下深度游攻略里的小众景点太棒了！相公山的日出一定要看，人少景美。', '桂林的美，不止于知名景点！');
INSERT INTO `discusstravelguide` VALUES (108, '2025-12-10 02:38:15', 108, 18, 'upload/users8.jpg', '账号8', '广西少数民族风情游攻略带我们去了融水苗寨，苗年的芦笙舞太震撼了，长桌宴的酸鱼酸肉很有特色。', '广西的少数民族文化，值得深入体验！');

-- ----------------------------
-- Table structure for feedback
-- ----------------------------
DROP TABLE IF EXISTS `feedback`;
CREATE TABLE `feedback`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `picture` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '反馈图片',
  `title` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '反馈名称',
  `feedbacktime` datetime(0) NULL DEFAULT NULL COMMENT '反馈时间',
  `content` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '反馈内容',
  `userid` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '用户id',
  `username` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '用户姓名',
  `tips` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '备注',
  `sfsh` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '待审核' COMMENT '是否审核',
  `shhf` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '审核回复',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `fankuibianhao`(`picture`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1753414904981 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '反馈意见' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of feedback
-- ----------------------------
INSERT INTO `feedback` VALUES (149, '2025-12-10 20:30:42', '1731241842870', '漓江竹筏预订建议', '2025-12-10 00:00:00', '希望平台能增加漓江竹筏的实时余票显示，每次订都不确定有没有票，只能打电话问很麻烦', '11', '李游客', '竹筏预订', '待审核', NULL);
INSERT INTO `feedback` VALUES (150, '2025-12-10 20:31:16', '1731241877339', '餐厅信息补充', '2025-12-10 00:00:00', '餐厅推荐里能不能标注是否需要提前排队，上次去谢三姐啤酒鱼等了1个多小时，影响行程', '12', '王女士', '餐厅排队', '待审核', NULL);
INSERT INTO `feedback` VALUES (151, '2025-12-10 20:32:28', '1731241949375', '景点导览优化', '2025-12-10 00:00:00', '龙脊梯田景区太大了，建议平台增加景区内的交通指引，比如从平安寨到金坑的班车时间', '13', '张先生', '梯田交通', '待审核', NULL);
INSERT INTO `feedback` VALUES (1753414904980, '2025-07-25 11:41:44', 'upload/1678358274781.jpg', '夜游两江四湖信息', '2025-07-25 00:00:00', '<p>想知道两江四湖夜游的游船有没有儿童优惠票，平台上没标注，另外能不能增加游船实时位置查询</p>', '1678358276062', '张4', '夜游优惠', '待审核', NULL);

-- ----------------------------
-- Table structure for merchant
-- ----------------------------
DROP TABLE IF EXISTS `merchant`;
CREATE TABLE `merchant`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `merchantusername` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '供应商账号',
  `password` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '密码',
  `merchantname` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '供应商名称',
  `merchantaddress` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '供应商地址',
  `email` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phonenumber` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `merchantlicense` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '营业执照',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `merchantusername`(`merchantusername`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1737870393174 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '供应商' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of merchant
-- ----------------------------
INSERT INTO `merchant` VALUES (21, '2025-12-30 03:17:27', 'nanning_travel', '1', '南宁文旅集团有限公司', '南宁市青秀区民族大道111号', 'nanning_travel@qq.com', '13877188881', 'upload/merchant_merchantlicense1.jpg');
INSERT INTO `merchant` VALUES (22, '2025-12-30 03:17:27', 'guilin_travel', '1', '桂林漓江景区管理有限公司', '桂林市阳朔县阳朔镇抗战路6号', 'guilin_travel@qq.com', '13877388882', 'upload/merchant_merchantlicense2.jpg');
INSERT INTO `merchant` VALUES (23, '2025-12-30 03:17:27', 'yangshuo_travel', '1', '阳朔遇龙河文旅运营公司', '桂林市阳朔县白沙镇遇龙河景区', 'yangshuo_travel@qq.com', '13877388883', 'upload/merchant_merchantlicense3.jpg');
INSERT INTO `merchant` VALUES (24, '2025-12-30 03:17:27', 'liuzhou_travel', '1', '柳州文旅发展集团', '柳州市城中区文昌路66号', 'liuzhou_travel@qq.com', '13877288884', 'upload/merchant_merchantlicense4.jpg');
INSERT INTO `merchant` VALUES (25, '2025-12-30 03:17:27', '1', '1', '广西文旅综合管理中心', '南宁市青秀区金湖南路24号', 'gx_travel@qq.com', '13877188885', 'upload/merchant_merchantlicense5.jpg');
INSERT INTO `merchant` VALUES (26, '2025-12-30 03:17:27', 'beihai_travel', '1', '北海银滩景区管理有限公司', '北海市银海区银滩大道8号', 'beihai_travel@qq.com', '13877988886', 'upload/merchant_merchantlicense6.jpg');
INSERT INTO `merchant` VALUES (27, '2025-12-30 03:17:27', 'bose_travel', '1', '百色靖西文旅管理公司', '百色市靖西市新靖镇绣球大道', 'bose_travel@qq.com', '13877688887', 'upload/merchant_merchantlicense7.jpg');
INSERT INTO `merchant` VALUES (28, '2025-12-30 03:17:27', 'hechi_travel', '1', '河池巴马文旅运营公司', '河池市巴马县甲篆镇长寿村', 'hechi_travel@qq.com', '13877888888', 'upload/merchant_merchantlicense8.jpg');

-- ----------------------------
-- Table structure for news
-- ----------------------------
DROP TABLE IF EXISTS `news`;
CREATE TABLE `news`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `title` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '标题',
  `introduction` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '简介',
  `picture` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '图片',
  `content` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '内容',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 139 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '公告信息' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of news
-- ----------------------------
INSERT INTO `news` VALUES (131, '2025-12-09 18:32:16', '2025年桂林漓江景区新增电子导览服务，支持多语言讲解', '漓江景区升级智慧服务，游客可通过景区公众号或小程序获取电子导览，涵盖中英日韩四语言，实时讲解沿途景点', 'upload/news1.jpg', '2025年7月起，桂林漓江景区正式上线电子导览服务。游客无需租赁实体导览器，只需扫描景区入口或游船码头的二维码，关注“桂林漓江景区”公众号即可获取电子导览。导览内容涵盖漓江全程20个核心景点，包括九马画山、黄布倒影、兴坪古镇等，支持中、英、日、韩四种语言切换。电子导览还提供实时定位、景点历史文化介绍、最佳拍摄点位推荐等功能，解决了传统导游讲解覆盖不足的问题。此外，景区还优化了核心区域WiFi信号，确保游客在游览过程中可流畅使用电子导览服务，提升游览体验');
INSERT INTO `news` VALUES (132, '2025-12-09 18:32:16', '2025桂林阳朔西街夜间文旅活动升级，新增非遗展演', '阳朔西街推出“夜游阳朔·非遗秀”活动，每周五、六晚举办民族山歌、侗族木构建筑技艺等非遗表演，丰富夜间旅游内容', 'upload/news2.jpg', '为进一步提升桂林夜间旅游品质，2025年7月起，阳朔西街夜间文旅活动正式升级。每周五、周六晚19:30-21:00，西街中心广场将举办“夜游阳朔·非遗秀”活动，邀请广西本地非遗传承人现场表演民族山歌对唱、侗族百家宴展示、瑶族刺绣技艺等传统艺术。活动期间，游客还可参与非遗体验互动，如学习壮锦编织、侗族油茶制作等。此外，西街部分商户延长营业时间至23:00，推出“桂北夜宴”特色美食套餐，涵盖桂林米粉、阳朔啤酒鱼等本地美食。桂林文旅部门表示，后续将根据游客反馈，逐步增加展演频次，丰富活动内容，打造桂林夜间旅游新名片');
INSERT INTO `news` VALUES (133, '2025-12-09 18:32:16', '北海银滩景区优化入园流程，支持刷脸入园', '北海银滩景区全面升级票务系统，游客可通过身份证、电子二维码或刷脸快速入园，减少排队时间', 'upload/news3.jpg', '为提升游客入园效率，北海银滩景区于2025年6月完成票务系统升级。此次升级后，游客可通过三种方式快速入园：一是凭身份证直接刷证入园；二是通过景区公众号或合作平台预订门票后，生成电子二维码扫码入园；三是已完成人脸录入的游客（提前在公众号上传人脸信息）可直接刷脸入园。据景区工作人员介绍，刷脸入园平均耗时仅3秒，相比传统人工检票效率提升60%，有效缓解节假日入园排队压力。此外，景区还在入口处增设了10台自助取票机，方便未提前购票的游客现场购票，进一步优化入园体验');
INSERT INTO `news` VALUES (134, '2025-12-09 18:32:16', '广西推出“桂林山水+壮乡文化”精品旅游线路，覆盖龙胜各族自治县', '广西文旅厅联合桂林市推出3条“山水+民族”精品线路，游客可深度体验桂林美景与民族文化', 'upload/news4.jpg', '2025年8月起，广西文旅厅联合桂林市推出3条“桂林山水+壮乡文化”精品线路，分别为“龙胜龙脊梯田2日游”“桂林+阳朔3日游”“南宁+桂林4日游”。线路核心亮点在于深度融合桂林山水景观与民族文化：游客可在龙胜观赏龙脊梯田美景，参与民族晒衣节活动，品尝壮家油茶宴（含竹筒饭、五色糯米饭等特色内容），晚上还可观看民族歌舞表演，学习民族民俗知识。此外，线路还包含专业导游带队、景区直通车接送、特色民宿住宿等服务，解决游客交通及住宿安排难题。据了解，该线路针对家庭游客还推出“亲子套餐”，增加壮锦手工制作等项目，适合不同游客群体需求');
INSERT INTO `news` VALUES (135, '2025-12-09 18:32:16', '2025崇左德天瀑布景区新增观光车停靠点，方便游客上下车', '德天瀑布景区在核心区域新增2个观光车停靠点，优化游览路线，同时增加观光车数量，减少游客等待时间', 'upload/news5.jpg', '为提升德天瀑布游览体验，崇左德天瀑布景区于2025年7月完成两项升级：一是在景区核心区域新增2个观光车停靠点，新增停靠点均配备游客休息区、卫生间及行李寄存处，方便游客根据行程灵活选择上下车地点；二是新增20辆观光车，观光车总数达到150辆，高峰时段可同时接待更多游客，将平均等待时间从1小时缩短至30分钟。此外，景区还对观光车进行了安全升级，每辆车配备GPS定位系统及应急救生设备，驾驶员需通过安全培训考核方可上岗。景区负责人表示，后续将根据游客流量变化，动态调整观光车运营数量，确保游客获得良好的游览体验');
INSERT INTO `news` VALUES (136, '2025-12-09 18:32:16', '南宁邕江夜游游船新增“南湖专属航线”', '邕江景区推出“南湖专属航线”，游船将在南湖湖心岛附近停留15分钟，方便游客拍摄夜景全景', 'upload/news6.jpg', '为满足游客拍摄需求，南宁邕江夜游游船于2025年6月新增“南湖专属航线”。该航线在原有夜游线路基础上，特别增加南湖湖心岛附近水域停留环节，游船将在此处停泊15分钟，游客可在船上拍摄南湖夜景全景（最佳拍摄时段为20:00-21:00）。此外，游船还配备专业摄影向导，为游客提供拍摄技巧指导，船上增设充电接口，方便游客及时导出照片。“南湖专属航线”每日仅开放2个班次（19:30、20:30），游客需提前1天通过景区公众号预订。据景区统计，该航线推出1个月内，预订量占夜游游船总预订量的40%，深受摄影爱好者及情侣游客喜爱');
INSERT INTO `news` VALUES (137, '2025-12-09 18:32:16', '桂菜烹饪技艺入选国家级非遗扩展项目，广西多家老字号推出体验课', '桂菜烹饪技艺正式入选国家级非物质文化遗产扩展项目，广西10家餐饮老字号推出“桂菜制作体验课”', 'upload/news7.jpg', '2025年5月，国务院公布第五批国家级非物质文化遗产代表性项目名录，桂菜烹饪技艺成功入选。为传承推广这一非遗技艺，广西10家餐饮老字号（包括南宁饭店、桂林酒家、柳州螺蛳粉小镇等）联合推出“桂菜制作体验课”。体验课时长1.5小时，游客可在专业厨师指导下，学习螺蛳粉、桂林米粉、巴马香猪等经典桂菜的制作流程，完成后可现场品尝自己制作的菜品。体验课适合亲子、情侣及美食爱好者，价格为88元/人，包含所有食材及工具使用。此外，老字号门店还设置了桂菜文化展示区，通过图片、实物展示桂菜的历史演变及文化内涵，让游客深入了解这一地方特色美食');
INSERT INTO `news` VALUES (138, '2025-12-09 18:32:16', '2025河池巴马长寿村景区开通“长寿养生专线”，配备专业养生导师', '巴马长寿村景区针对养生爱好者推出“长寿养生专线”，每周六组织养生活动，配备专业养生导师及中医指导', 'upload/news8.jpg', '河池巴马作为世界著名的长寿之乡，因独特的地磁、水、空气环境，成为养生胜地。2025年7月起，巴马长寿村景区正式开通“长寿养生专线”。该专线每周六9:00-16:00在长寿村核心区举办，景区配备3名专业养生导师（包含中医、营养师），现场讲解巴马长寿秘诀及养生知识。参与专线的游客还可获得“巴马养生手册”，包含长寿食谱、养生功法教学等内容。此外，景区还推出“养生+住宿”套餐，游客可入住长寿村民宿，体验当地长寿老人的生活方式。据景区介绍，每年10-12月是巴马养生最佳时段，此时气候宜人，适合开展养生活动。该专线需提前2天通过景区官网预订，每期限定30人，确保养生体验');

-- ----------------------------
-- Table structure for scenicinfo
-- ----------------------------
DROP TABLE IF EXISTS `scenicinfo`;
CREATE TABLE `scenicinfo`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `scenicname` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '景点名称',
  `address` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '景点地址',
  `sceniclevel` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '景点等级',
  `price` int(0) NULL DEFAULT NULL COMMENT '门票价格',
  `telephone` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '景点电话',
  `opentime` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '开放时间',
  `introduce` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '景点介绍',
  `scenicphoto` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '景点图片',
  `thumbsupnum` int(0) NULL DEFAULT 0 COMMENT '赞',
  `crazilynum` int(0) NULL DEFAULT 0 COMMENT '踩',
  `clicktime` datetime(0) NULL DEFAULT NULL COMMENT '最近点击时间',
  `clicknum` int(0) NULL DEFAULT 0 COMMENT '点击次数',
  `content` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '简介',
  `rating` double(8, 1) NULL DEFAULT 0.0 COMMENT '评分',
  `merchantusername` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '供应商账号',
  `merchantname` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '供应商名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1678358486419 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '景点信息' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of scenicinfo
-- ----------------------------
INSERT INTO `scenicinfo` VALUES (31, '2025-12-09 18:32:15', '漓江景区', '桂林市阳朔县阳朔镇抗战路6号', 'AAAAA', 210, '0773-8822666', '08:00-17:00（冬季08:30-16:30）', '世界自然遗产，以“山青、水秀、洞奇、石美”四绝闻名。核心景点包括象鼻山、九马画山、黄布倒影等。可乘竹筏漂流（230元）体验“百里漓江，百里画廊”，春季烟雨漓江最美，秋季观红叶，适合摄影与休闲', 'upload/scenic1.jpg', 329, 15, '2025-12-23 16:48:34', 1294, '广西旅游核心，世界自然遗产，建议安排1-2天游览', 8.0, 'nanning_travel', '南宁文旅集团有限公司');
INSERT INTO `scenicinfo` VALUES (32, '2025-12-09 18:32:15', '德天跨国瀑布景区', '崇左市大新县硕龙镇德天村', 'AAAAA', 115, '0771-3690999', '07:30-17:30', '亚洲第一、世界第四大跨国瀑布，横跨中越两国。核心景点包括德天瀑布、53号界碑、中越集市等，可体验竹筏近距离观赏瀑布，适合摄影与边境风情体验', 'upload/scenic2.jpg', 295, 12, '2025-11-10 15:21:28', 989, '中越边境标志性景点，跨国瀑布奇观，建议安排1天', 8.0, 'guilin_travel', '桂林漓江景区管理有限公司');
INSERT INTO `scenicinfo` VALUES (33, '2025-12-09 18:32:15', '银滩景区', '北海市银海区银滩中路', 'AAAA', 0, '0779-3888888', '全天开放', '被誉为“天下第一滩”，以“滩长平、沙细白、水温净、浪柔软”著称。核心景点包括银滩公园、海滩公园、情人岛等，适合沙滩休闲、水上运动，夏季海水温度适宜游泳', 'upload/scenic3.jpg', 268, 9, '2025-11-10 15:29:01', 846, '北海标志性海滩，免费开放，适合亲子游与休闲', 8.0, 'yangshuo_travel', '阳朔遇龙河文旅运营公司');
INSERT INTO `scenicinfo` VALUES (34, '2025-12-09 18:32:15', '巴马长寿村', '河池市巴马县甲篆镇坡月村', 'AAAA', 80, '0778-6218888', '08:00-17:30', '世界长寿之乡，以长寿文化、养生环境闻名。核心景点包括百魔洞、百鸟岩、长寿村等，可体验瑶族养生文化、品尝火麻汤等长寿食品，适合养生度假与研学旅行', 'upload/scenic4.jpg', 245, 7, '2025-11-10 15:29:00', 763, '世界长寿之乡，适合养生度假，建议安排2-3天', 8.0, 'liuzhou_travel', '柳州文旅发展集团');
INSERT INTO `scenicinfo` VALUES (35, '2025-12-09 18:32:15', '青秀山景区', '南宁市青秀区凤岭南路6号', 'AAAAA', 20, '0771-5560666', '07:00-18:00', '南宁城市绿肺，以亚热带植物、邕江风光为特色。核心景点包括龙象塔、苏铁园、桃花岛等，山顶可俯瞰南宁城区全景，春季桃花盛开时景色最佳', 'upload/scenic5.jpg', 218, 8, '2025-11-10 15:29:02', 699, '南宁城市名片，休闲度假胜地，建议安排半天', 8.0, '1', '广西文旅综合管理中心');
INSERT INTO `scenicinfo` VALUES (36, '2025-12-09 18:32:15', '遇龙河景区', '桂林市阳朔县白沙镇遇龙村', 'AAAA', 230, '0773-8818888', '08:00-17:00', '被誉为“小漓江”，以竹筏漂流、田园风光为特色。核心景点包括遇龙桥、富里桥、旧县村等，可体验人工撑筏漂流（230元），两岸田园风光如画，适合摄影与休闲', 'upload/scenic6.jpg', 186, 6, '2025-11-10 15:29:01', 544, '阳朔精华景区，竹筏漂流体验佳，建议安排半天', 8.0, 'beihai_travel', '北海银滩景区管理有限公司');
INSERT INTO `scenicinfo` VALUES (37, '2025-12-09 18:32:15', '龙潭公园', '柳州市鱼峰区龙潭路43号', 'AAAA', 0, '0772-3173378', '06:30-22:00', '以喀斯特地貌、侗族风情为特色。核心景点包括风雨桥、鼓楼、镜湖等，园内有4A景区大龙潭，可体验侗族歌舞表演，适合休闲散步与民俗体验', 'upload/scenic7.jpg', 152, 5, '2025-11-10 15:29:03', 490, '柳州城市公园，免费开放，适合休闲游', 8.0, 'bose_travel', '百色靖西文旅管理公司');
INSERT INTO `scenicinfo` VALUES (38, '2025-12-09 18:32:15', '通灵大峡谷', '百色市靖西市湖润镇新灵村', 'AAAA', 115, '0776-6218888', '08:00-17:00', '集峡谷、瀑布、溶洞为一体的自然奇观。核心景点包括通灵大瀑布、地下暗河、古悬洞葬等，峡谷内负氧离子含量高，适合徒步探险与避暑', 'upload/scenic8.jpg', 312, 18, '2025-11-10 15:50:59', 1573, '靖西自然奇观，徒步探险胜地，建议安排1天', 8.0, 'hechi_travel', '河池巴马文旅运营公司');
INSERT INTO `scenicinfo` VALUES (40, '2025-12-09 18:32:15', '涠洲岛', '北海市海城区涠洲镇', 'AAAAA', 98, '0779-6016278', '全天开放', '中国最年轻的火山岛，以火山地貌、海岛风光为特色。核心景点包括鳄鱼山火山公园、滴水丹屏、石螺口海滩等，可看海上日出日落，体验海鲜大餐', 'upload/scenic9.jpg', 289, 12, '2025-11-10 15:21:28', 1134, '火山海岛，四季皆宜，建议安排2-3天', 9.0, 'beihai_travel', '北海银滩景区管理有限公司');
INSERT INTO `scenicinfo` VALUES (41, '2025-12-09 18:32:15', '象鼻山景区', '桂林市秀峰区象山街道1号', 'AAAAA', 75, '0773-2828999', '06:30-18:30', '桂林城徽，以"神象饮水"奇观闻名。核心景点包括水月洞、象眼岩、普贤塔等，园内桂花飘香，可乘船欣赏漓江美景', 'upload/scenic10.jpg', 178, 6, '2025-11-10 15:29:01', 523, '桂林标志性景点，打卡必去，建议安排2小时', 8.5, 'guilin_travel', '桂林漓江景区管理有限公司');
INSERT INTO `scenicinfo` VALUES (42, '2025-12-09 18:32:15', '龙脊梯田', '桂林市龙胜各族自治县龙脊镇', 'AAAA', 100, '0773-7588988', '全天开放', '世界梯田原乡，以壮族、瑶族梯田文化为特色。核心景点包括平安壮寨梯田、金坑大寨梯田等，春季水镜梯田、秋季金色稻浪最美', 'upload/scenic11.jpg', 265, 15, '2025-11-10 15:29:00', 1067, '世界梯田奇观，摄影胜地，建议安排1-2天', 9.0, 'guilin_travel', '桂林漓江景区管理有限公司');
INSERT INTO `scenicinfo` VALUES (43, '2025-12-09 18:32:15', '黄姚古镇', '贺州市昭平县黄姚镇1号', 'AAAA', 100, '0774-6722688', '07:30-18:00', '中国最美古镇之一，以明清建筑、古榕树为特色。核心景点包括文明阁、宝珠观、古戏台等，青石板路、小桥流水，适合悠闲漫步', 'upload/scenic12.jpg', 234, 10, '2025-11-10 15:29:02', 876, '千年古镇，休闲度假，建议安排1天', 8.5, 'hezhou_travel', '贺州文旅发展有限公司');
INSERT INTO `scenicinfo` VALUES (44, '2025-12-09 18:32:15', '独秀峰·靖江王府', '桂林市秀峰区王城路1号', 'AAAAA', 130, '0773-2888888', '07:30-18:00', '桂林山水甲天下，阅尽王城知桂林。核心景点包括独秀峰、靖江王府、贡院等，可登顶俯瞰桂林全景，体验明清科举文化', 'upload/scenic13.jpg', 201, 8, '2025-11-10 15:29:01', 689, '桂林历史文化核心景区，建议安排半天', 8.0, 'guilin_travel', '桂林漓江景区管理有限公司');
INSERT INTO `scenicinfo` VALUES (45, '2025-12-09 18:32:15', '程阳八寨', '柳州市三江县林溪镇', 'AAAA', 90, '0772-8586868', '08:00-17:30', '中国最美侗族村寨，以风雨桥、侗族大歌闻名。核心景点包括程阳风雨桥、马鞍寨、岩寨等，可体验侗族百家宴、听侗族大歌', 'upload/scenic14.jpg', 195, 7, '2025-11-10 15:29:03', 612, '侗族文化活化石，民族风情浓厚，建议安排1天', 8.5, 'liuzhou_travel', '柳州文旅发展集团');
INSERT INTO `scenicinfo` VALUES (46, '2025-12-09 18:32:15', '明仕田园', '崇左市大新县堪圩乡', 'AAAA', 120, '0771-3728888', '08:00-18:00', '被誉为"小桂林"，以喀斯特山水田园风光为特色。核心景点包括明仕河、明仕田园度假区等，可竹筏漂流，欣赏《花千骨》取景地', 'upload/scenic15.jpg', 188, 9, '2025-11-10 15:29:00', 556, '中越边境田园风光，休闲度假胜地，建议安排1天', 8.0, 'chongzuo_travel', '崇左德天景区管理公司');
INSERT INTO `scenicinfo` VALUES (47, '2025-12-09 18:32:15', '红树林保护区', '北海市银海区银滩东边', 'AAA', 0, '0779-3211122', '全天开放', '中国最大的红树林保护区之一，以滨海湿地生态为特色。可观赏白鹭、招潮蟹等海洋生物，适合生态观光与科普教育', 'upload/scenic16.jpg', 112, 5, '2025-11-10 15:29:02', 387, '滨海湿地生态，免费开放，建议安排半天', 7.5, 'beihai_travel', '北海银滩景区管理有限公司');
INSERT INTO `scenicinfo` VALUES (48, '2025-12-09 18:32:15', '鹅泉景区', '百色市靖西市新靖镇鹅泉村', 'AAAA', 48, '0776-6181111', '07:30-18:30', '靖西古八景之首，以泉水、田园风光为特色。核心景点包括鹅泉、灵泉晚照等，泉水清澈见底，夏季可戏水纳凉', 'upload/scenic17.jpg', 156, 4, '2025-11-10 15:29:01', 398, '靖西南国风光，免费开放，建议安排半天', 8.0, 'bose_travel', '百色靖西文旅管理公司');
INSERT INTO `scenicinfo` VALUES (49, '2025-12-09 18:32:15', '桂林两江四湖', '桂林市秀峰区解放东路1号', 'AAAAA', 210, '0773-2888899', '全天开放', '桂林城市名片，以夜游漓江、杉湖、榕湖、桂湖、木龙湖为特色。可乘船欣赏日月双塔、玻璃桥等夜景，灯光秀震撼', 'upload/scenic18.jpg', 245, 11, '2025-11-10 15:29:00', 745, '桂林夜景精华，夜游推荐，建议安排2小时', 8.5, 'guilin_travel', '桂林漓江景区管理有限公司');
INSERT INTO `scenicinfo` VALUES (50, '2025-12-09 18:32:15', '涠洲岛天主教堂', '北海市海城区涠洲镇盛塘村', 'AAA', 0, '0779-6016278', '08:00-17:30', '法国哥特式建筑，百年天主教堂。以哥特式尖塔、彩绘玻璃为特色，是涠洲岛标志性人文景观', 'upload/scenic19.jpg', 89, 3, '2025-11-10 15:29:02', 267, '涠洲岛人文地标，免费开放，建议安排1小时', 7.5, 'beihai_travel', '北海银滩景区管理有限公司');
INSERT INTO `scenicinfo` VALUES (51, '2025-12-09 18:32:15', '乐满地度假世界', '桂林市兴安县志玲路', 'AAAAA', 220, '0773-6222888', '09:00-17:30', '大型主题乐园，以游乐设施、度假酒店为特色。核心景点包括欢乐中国城、美国西部区等，适合亲子游与团队建设', 'upload/scenic20.jpg', 198, 14, '2025-11-10 15:29:01', 678, '桂林主题乐园，亲子游首选，建议安排1天', 8.0, 'guilin_travel', '桂林漓江景区管理有限公司');
INSERT INTO `scenicinfo` VALUES (52, '2025-12-09 18:32:15', '百色起义纪念馆', '百色市右江区迎龙山路', 'AAAA', 0, '0776-2836688', '09:00-17:00', '全国红色旅游经典景区，以百色起义历史为特色。核心景点包括纪念馆、起义旧址等，可了解邓小平、张云逸等革命历史', 'upload/scenic21.jpg', 134, 6, '2025-11-10 15:29:00', 423, '红色教育基地，免费参观，建议安排2小时', 8.0, 'bose_travel', '百色红色旅游管理中心');
INSERT INTO `scenicinfo` VALUES (53, '2025-12-09 18:32:15', '八角寨', '桂林市资源县梅溪镇', 'AAAA', 80, '0773-4488666', '07:30-18:00', '丹霞地貌精华，以"八角寨"丹霞峰林为特色。核心景点包括群螺观天、龙头香等，可欣赏丹霞地貌奇观', 'upload/scenic22.jpg', 175, 7, '2025-11-10 15:29:02', 512, '丹霞地貌奇观，摄影胜地，建议安排半天', 8.0, 'guilin_travel', '桂林漓江景区管理有限公司');
INSERT INTO `scenicinfo` VALUES (54, '2025-12-09 18:32:15', '南宁大明山', '南宁市武鸣区两江镇', 'AAAA', 128, '0771-6096888', '08:00-17:00', '广西庐山之称，以山地风光、避暑气候为特色。核心景点包括龙门峡谷、天台打卡点等，夏季避暑胜地，可看云海日出', 'upload/scenic23.jpg', 167, 8, '2025-11-10 15:29:01', 489, '南宁后花园，避暑胜地，建议安排1天', 8.0, 'nanning_travel', '南宁文旅集团有限公司');
INSERT INTO `scenicinfo` VALUES (55, '2025-12-09 18:32:15', '古龙山峡谷群', '百色市靖西市湖润镇', 'AAAA', 115, '0776-6218888', '08:00-17:30', '以峡谷、瀑布、暗河为特色。核心景点包括古龙山瀑布、通灵大峡谷等，可徒步探险、体验地下暗河漂流', 'upload/scenic24.jpg', 189, 10, '2025-11-10 15:29:00', 623, '峡谷探险胜地，建议安排1天', 8.0, 'bose_travel', '百色靖西文旅管理公司');
INSERT INTO `scenicinfo` VALUES (56, '2025-12-09 18:32:15', '桂林园博园', '桂林市雁山区雁山镇', 'AAAA', 60, '0773-3566888', '08:30-17:30', '以园林景观、花卉展览为特色。核心景点包括广西园、华夏园、国际园等，春季花展最盛', 'upload/scenic25.jpg', 145, 5, '2025-11-10 15:29:02', 378, '园林艺术展示，春季赏花好去处，建议安排半天', 7.5, 'guilin_travel', '桂林漓江景区管理有限公司');
INSERT INTO `scenicinfo` VALUES (57, '2025-12-09 18:32:15', '崇左石景林', '崇左市江州区城南新区', 'AAAA', 50, '0771-7836666', '08:00-18:00', '以喀斯特石林地貌为特色。核心景点包括石景林、神仙台等，与云南石林相似但规模较小', 'upload/scenic26.jpg', 123, 4, '2025-11-10 15:29:01', 301, '小型石林地貌，免费开放，建议安排2小时', 7.0, 'chongzuo_travel', '崇左德天景区管理公司');
INSERT INTO `scenicinfo` VALUES (58, '2025-12-09 18:32:15', '贺州黄姚古镇', '贺州市昭平县黄姚镇', 'AAAA', 88, '0774-6722688', '07:30-18:00', '中国最具旅游价值古古镇，以明清古建筑、风水文化为特色。核心景点包括宝珠观、古戏台、文明阁等', 'upload/scenic27.jpg', 198, 8, '2025-11-10 15:29:00', 534, '千年古镇，历史文化深厚，建议安排1天', 8.5, 'hezhou_travel', '贺州文旅发展有限公司');
INSERT INTO `scenicinfo` VALUES (59, '2025-12-09 18:32:15', '桂林东西巷', '桂林市秀峰区正阳路', 'AAA', 0, '0773-2888888', '全天开放', '桂林历史文化街区，以明清古建筑、小吃美食为特色。可品尝桂林米粉、油茶等特色美食，购买伴手礼', 'upload/scenic28.jpg', 87, 3, '2025-11-10 15:29:02', 256, '桂林美食街区，免费逛吃，建议安排2小时', 7.5, 'guilin_travel', '桂林漓江景区管理有限公司');
INSERT INTO `scenicinfo` VALUES (60, '2025-12-09 18:32:15', '三江布央茶园', '柳州市三江县八江镇布央村', 'AAAA', 60, '0772-8586868', '08:00-17:30', '以侗族茶园、梯田风光为特色。核心景点包括仙人山茶园、四季花海等，可体验采茶制茶，品尝三江早春茶', 'upload/scenic29.jpg', 134, 5, '2025-11-10 15:29:01', 367, '侗族茶园文化，休闲品茗，建议安排半天', 7.5, 'liuzhou_travel', '柳州文旅发展集团');
INSERT INTO `scenicinfo` VALUES (61, '2025-12-09 18:32:15', '友谊关景区', '崇左市凭祥市友谊镇', 'AAAA', 55, '0771-8533333', '08:00-17:30', '中国九大名关之一，中越边境地标。核心景点包括友谊关、关楼陈列馆等，可感受中越边境贸易氛围', 'upload/scenic30.jpg', 156, 6, '2025-11-10 15:29:00', 445, '中越边境国门，历史文化厚重，建议安排2小时', 8.0, 'chongzuo_travel', '崇左德天景区管理公司');
INSERT INTO `scenicinfo` VALUES (62, '2025-12-09 18:32:15', '大芦村', '钦州市灵山县佛子镇', '中国历史文化名村', 30, '0777-6988888', '08:00-18:00', '广西历史文化名村，以明清古建筑、楹联文化为特色。核心景点包括劳氏古建筑群、镬耳楼等，可了解岭南民俗文化', 'upload/scenic31.jpg', 98, 3, '2025-11-10 15:29:02', 234, '岭南古村落，免费参观，建议安排2小时', 7.0, 'qinzhou_travel', '钦州文旅发展有限公司');
INSERT INTO `scenicinfo` VALUES (63, '2025-12-09 18:32:15', '梧州骑楼城', '梧州市万秀区骑楼城', 'AAAA', 0, '0774-2828888', '全天开放', '中国最大的骑楼建筑群，以民国骑楼建筑、岭南文化为特色。可品尝梧州龟苓膏、纸包鸡等特色美食', 'upload/scenic32.jpg', 76, 4, '2025-11-10 15:29:01', 289, '骑楼建筑群，免费游览，建议安排半天', 7.5, 'wuzhou_travel', '梧州文化旅游管理公司');
INSERT INTO `scenicinfo` VALUES (64, '2025-12-09 18:32:15', '桂平西山', '贵港市桂平市西山路3号', 'AAAA', 100, '0775-3382888', '07:30-18:00', '国家重点风景名胜区，以佛教文化、登山健身为特色。核心景点包括龙华寺、乳泉亭等，可品尝西山茶', 'upload/scenic33.jpg', 143, 7, '2025-11-10 15:29:00', 412, '佛教圣地，登山健身，建议安排半天', 8.0, 'guigang_travel', '贵港文旅发展公司');
INSERT INTO `scenicinfo` VALUES (65, '2025-12-09 18:32:15', '金秀圣堂山', '来宾市金秀县忠良乡', 'AAAA', 85, '0772-6218888', '08:00-17:00', '瑶族圣山，以高山草甸、云海日出为特色。核心景点包括圣堂山杜鹃花海、云海观景台等，夏季杜鹃花盛开时最美', 'upload/scenic34.jpg', 167, 9, '2025-11-10 15:29:02', 498, '瑶族神山，云海日出，建议安排1天', 8.0, 'laibin_travel', '来宾文旅发展有限公司');
INSERT INTO `scenicinfo` VALUES (66, '2025-12-09 18:32:15', '钦州三娘湾', '钦州市犀牛脚镇三娘湾村', 'AAAA', 50, '0777-3818888', '08:00-18:00', '中华白海豚之乡，以海豚观赏、海滨风光为特色。可出海观赏野生白海豚，品尝钦州大蚝', 'upload/scenic35.jpg', 178, 6, '2025-11-10 15:29:01', 456, '海豚观赏胜地，亲子游推荐，建议安排半天', 8.0, 'qinzhou_travel', '钦州文旅发展有限公司');
INSERT INTO `scenicinfo` VALUES (67, '2025-12-09 18:32:15', '防城港金滩', '防城港市东兴市江平镇万尾岛', 'AAA', 0, '0770-2288888', '全天开放', '北部湾海滨浴场，以金色沙滩、京族风情为特色。可体验京族拉大网、品尝京族美食', 'upload/scenic36.jpg', 89, 4, '2025-11-10 15:29:00', 278, '金色沙滩，京族风情，免费开放，建议安排半天', 7.5, 'fangchenggang_travel', '防城港文旅管理公司');
INSERT INTO `scenicinfo` VALUES (68, '2025-12-09 18:32:15', '玉林云天宫', '玉林市江州区玉柴路', 'AAAA', 150, '0775-2888888', '08:00-17:30', '以仿古建筑、佛教文化为特色。核心景点包括云天宫、石雕艺术馆等，建筑宏伟壮观', 'upload/scenic37.jpg', 134, 5, '2025-11-10 15:29:02', 367, '仿古建筑群，佛教文化，建议安排半天', 7.5, 'yulin_travel', '玉林文旅发展公司');
INSERT INTO `scenicinfo` VALUES (69, '2025-12-09 18:32:15', '贵港北帝山', '贵港市平南县大鹏镇', 'AAAA', 95, '0775-7768888', '08:00-17:00', '广西张家界之称，以丹霞地貌、峡谷风光为特色。核心景点包括北帝山、峡谷栈道等，可欣赏丹霞奇观', 'upload/scenic38.jpg', 156, 8, '2025-11-10 15:29:01', 445, '丹霞地貌，峡谷探险，建议安排1天', 8.0, 'guigang_travel', '贵港文旅发展公司');
INSERT INTO `scenicinfo` VALUES (70, '2025-12-09 18:32:15', '来宾圣堂山', '来宾市金秀县罗香乡', 'AAAA', 85, '0772-6218888', '08:00-17:00', '以瑶族文化、高山草甸为特色。核心景点包括圣堂山万亩草甸、云海日出等，夏季避暑胜地', 'upload/scenic39.jpg', 145, 7, '2025-11-10 15:29:00', 389, '瑶族圣山，高山草甸，建议安排1天', 8.0, 'laibin_travel', '来宾文旅发展有限公司');
INSERT INTO `scenicinfo` VALUES (71, '2025-12-09 18:32:15', '百色大王岭', '百色市右江区大楞乡', 'AAAA', 70, '0776-2888888', '08:00-17:30', '以原始森林、漂流为特色。核心景点包括大王岭漂流、原始森林步道等，夏季漂流惊险刺激', 'upload/scenic40.jpg', 123, 6, '2025-11-10 15:29:02', 334, '原始森林，漂流胜地，建议安排半天', 7.5, 'bose_travel', '百色红色旅游管理中心');
INSERT INTO `scenicinfo` VALUES (72, '2025-12-09 18:32:15', '桂林穿山公园', '桂林市七星区穿山路', 'AAAA', 55, '0773-5818888', '07:00-18:00', '以穿山溶洞、塔山为特色。核心景点包括穿山岩、月岩、塔山等，可欣赏喀斯特溶洞奇观', 'upload/scenic41.jpg', 98, 4, '2025-11-10 15:29:01', 256, '喀斯特溶洞，免费参观，建议安排2小时', 7.5, 'guilin_travel', '桂林漓江景区管理有限公司');
INSERT INTO `scenicinfo` VALUES (73, '2025-12-09 18:32:15', '柳州百里柳江', '柳州市城中区滨江东路', 'AAAAA', 0, '0772-2828888', '全天开放', '柳州城市景观带，以柳江夜游、百里画廊为特色。可乘船欣赏柳江夜景、人工瀑布', 'upload/scenic42.jpg', 112, 5, '2025-11-10 15:29:00', 312, '柳江夜游，城市夜景，建议安排2小时', 8.0, 'liuzhou_travel', '柳州文旅发展集团');
INSERT INTO `scenicinfo` VALUES (74, '2025-12-09 18:32:15', '北海海底世界', '北海市银海区银滩岸边', 'AAAA', 138, '0779-2062688', '08:30-17:30', '以海洋生物展示、海底隧道为特色。可欣赏鲨鱼、海龟等海洋生物，适合亲子科普教育', 'upload/scenic43.jpg', 145, 6, '2025-11-10 15:29:02', 389, '海洋馆，亲子游推荐，建议安排2小时', 7.5, 'beihai_travel', '北海银滩景区管理有限公司');
INSERT INTO `scenicinfo` VALUES (75, '2025-12-09 18:32:15', '桂林七星公园', '桂林市七星区七星路1号', 'AAAA', 75, '0773-5818888', '06:30-19:00', '桂林最大综合性公园，以七星岩、骆驼峰为特色。核心景点包括七星岩、龙隐洞、骆驼峰等', 'upload/scenic44.jpg', 134, 7, '2025-11-10 15:29:01', 401, '桂林城市公园，休闲漫步，建议安排半天', 8.0, 'guilin_travel', '桂林漓江景区管理有限公司');
INSERT INTO `scenicinfo` VALUES (76, '2025-12-09 18:32:15', '南宁花花大世界', '南宁市武鸣区双桥镇伊岭工业区', 'AAAA', 80, '0771-6266666', '08:30-17:30', '以花卉苗木、生态休闲为特色。核心景点包括花卉园、生态农业园等，春季花展最盛', 'upload/scenic45.jpg', 98, 5, '2025-11-10 15:29:00', 278, '花卉主题公园，春季赏花，建议安排半天', 7.5, 'nanning_travel', '南宁文旅集团有限公司');
INSERT INTO `scenicinfo` VALUES (77, '2025-12-09 18:32:15', '河池巴马水晶宫', '河池市巴马县那社乡', 'AAAA', 150, '0778-6218888', '08:00-17:00', '以溶洞奇观、水晶宫为特色。核心景点包括水晶宫、地下暗河等，溶洞内钟乳石晶莹剔透', 'upload/scenic46.jpg', 167, 8, '2025-11-10 15:29:02', 445, '溶洞奇观，地质研学，建议安排2小时', 8.0, 'hechi_travel', '河池巴马文旅运营公司');
INSERT INTO `scenicinfo` VALUES (78, '2025-12-09 18:32:15', '贺州姑婆山', '贺州市八步区姑婆山风景区', 'AAAA', 85, '0774-5236888', '08:00-17:00', '以森林氧吧、瀑布群为特色。核心景点包括姑婆山顶、仙姑瀑布等，空气负氧离子含量高', 'upload/scenic47.jpg', 134, 7, '2025-11-10 15:29:01', 378, '森林氧吧，避暑胜地，建议安排1天', 8.0, 'hezhou_travel', '贺州文旅发展有限公司');
INSERT INTO `scenicinfo` VALUES (79, '2025-12-09 18:32:15', '崇左黑水河', '崇左市大新县雷平镇', 'AAAA', 120, '0771-3728888', '08:00-17:30', '以山水风光、竹筏漂流为特色。核心景点包括黑水河、水上森林等，可乘竹筏欣赏两岸风光', 'upload/scenic48.jpg', 156, 9, '2025-11-10 15:29:00', 423, '山水风光，竹筏漂流，建议安排半天', 8.0, 'chongzuo_travel', '崇左德天景区管理公司');
INSERT INTO `scenicinfo` VALUES (80, '2025-12-09 18:32:15', '防城港白浪滩', '防城港市港口区企沙镇', 'AAA', 0, '0770-2288888', '全天开放', '以黑色沙滩、海滨风光为特色。沙滩呈现独特黑色，可体验海滨浴场', 'upload/scenic49.jpg', 67, 3, '2025-11-10 15:29:02', 189, '黑沙滩奇观，免费开放，建议安排2小时', 7.0, 'fangchenggang_travel', '防城港文旅管理公司');
INSERT INTO `scenicinfo` VALUES (81, '2025-12-09 18:32:15', '玉林大容山', '玉林市北流市民乐镇', 'AAAA', 60, '0775-6668888', '08:00-17:30', '以高山草甸、云海日出为特色。核心景点包括大容山顶、莲花湖等，夏季避暑胜地', 'upload/scenic50.jpg', 123, 6, '2025-11-10 15:29:01', 334, '高山草甸，云海日出，建议安排1天', 8.0, 'yulin_travel', '玉林文旅发展公司');

-- ----------------------------
-- Table structure for storeup
-- ----------------------------
DROP TABLE IF EXISTS `storeup`;
CREATE TABLE `storeup`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `userid` bigint(0) NOT NULL COMMENT '用户id',
  `refid` bigint(0) NULL DEFAULT NULL COMMENT '商品id',
  `tablename` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '表名',
  `name` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '名称',
  `picture` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '图片',
  `type` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '1' COMMENT '类型(1:收藏,21:赞,22:踩,31:竞拍参与,41:关注)',
  `inteltype` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '推荐类型',
  `remark` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1762762685805 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '收藏表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of storeup
-- ----------------------------
INSERT INTO `storeup` VALUES (1766479716616, '2025-12-23 16:48:36', 1678358276062, 31, 'scenicinfo', '漓江景区', 'upload/scenic1.jpg', '1', NULL, NULL);

-- ----------------------------
-- Table structure for ticketreserve
-- ----------------------------
DROP TABLE IF EXISTS `ticketreserve`;
CREATE TABLE `ticketreserve`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `scenicname` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '景点名称',
  `address` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '景点地址',
  `price` float NULL DEFAULT NULL COMMENT '门票价格',
  `sceniclevel` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '景点等级',
  `reservetime` datetime(0) NULL DEFAULT NULL COMMENT '预订时间',
  `remark` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '备注',
  `username` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '账号',
  `name` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `telephone` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '手机',
  `ispay` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '未支付' COMMENT '是否支付',
  `refid` bigint(0) NULL DEFAULT NULL COMMENT '父id',
  `rating` double(8, 1) NULL DEFAULT 0.0 COMMENT '评分',
  `review` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '评论',
  `sfsh` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '待审核' COMMENT '审核状态',
  `shhf` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '审核回复',
  `avatar` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '头像',
  `merchantusername` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '供应商账号',
  `merchantname` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '供应商名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1766479697965 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '门票预订' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ticketreserve
-- ----------------------------
INSERT INTO `ticketreserve` VALUES (41, '2025-12-09 18:32:15', '漓江景区', '桂林市阳朔县阳朔镇抗战路6号', 210, 'AAAAA', '2025-08-06 09:00:00', '已预约漓江竹筏漂流，凭身份证入园，建议上午游览九马画山', '账号1', '李伟', '13877123451', '已支付', 31, 8.0, '景色不错', '已评价', NULL, NULL, 'guilin_travel', '桂林漓江景区管理有限公司');
INSERT INTO `ticketreserve` VALUES (42, '2025-12-09 18:32:15', '德天跨国瀑布景区', '崇左市大新县硕龙镇德天村', 115, 'AAAAA', '2025-08-08 09:00:00', '预订景区观光车，含中越边境讲解服务，需提前30分钟到游客中心', '账号2', '陈芳', '13877323452', '已支付', 32, 8.0, '景色不错', '已评价', NULL, NULL, 'bose_travel', '百色靖西文旅管理公司');
INSERT INTO `ticketreserve` VALUES (43, '2025-12-09 18:32:15', '银滩景区', '北海市银海区银滩中路', 0, 'AAAA', '2025-08-10 10:00:00', '含沙滩椅租赁，建议先游览银滩公园，再到海滩公园看音乐喷泉', '账号3', '黄明', '13877223453', '已支付', 33, 8.0, '景色不错', '已评价', NULL, NULL, 'beihai_travel', '北海银滩景区管理有限公司');
INSERT INTO `ticketreserve` VALUES (44, '2025-12-09 18:32:15', '巴马长寿村', '河池市巴马县甲篆镇坡月村', 80, 'AAAA', '2025-08-12 08:30:00', '预订长寿文化讲解服务，含百魔洞门票，需8:30前到游客中心', '账号4', '杨丽', '13877923454', '已支付', 34, 8.0, '景色不错', '已评价', NULL, NULL, 'hechi_travel', '河池巴马文旅运营公司');
INSERT INTO `ticketreserve` VALUES (45, '2025-12-09 18:32:15', '青秀山景区', '南宁市青秀区凤岭南路6号', 20, 'AAAAA', '2025-08-15 09:30:00', '含景区观光车，需穿防晒衣物，山上有茶水供应', '账号5', '赵强', '13877623455', '已支付', 35, 8.0, '景色不错', '已评价', NULL, NULL, 'nanning_travel', '南宁文旅集团有限公司');
INSERT INTO `ticketreserve` VALUES (46, '2025-12-09 18:32:15', '遇龙河景区', '桂林市阳朔县白沙镇遇龙村', 230, 'AAAA', '2025-08-18 10:00:00', '计划乘坐遇龙河竹筏漂流，需穿防滑鞋，带防晒用品', '账号6', '韦丽', '13877823456', '已支付', 36, 8.0, '景色不错', '已评价', NULL, NULL, 'yangshuo_travel', '阳朔遇龙河文旅运营公司');
INSERT INTO `ticketreserve` VALUES (47, '2025-12-09 18:32:15', '龙潭公园', '柳州市鱼峰区龙潭路43号', 0, 'AAAA', '2026-08-13 09:30:00', '含侗族歌舞表演观赏，建议先逛镜湖，再看风雨桥', '账号7', '周健', '13877123457', '已支付', 37, 8.0, '景色不错', '已评价', NULL, NULL, 'liuzhou_travel', '柳州文旅发展集团');
INSERT INTO `ticketreserve` VALUES (48, '2025-12-09 18:32:15', '通灵大峡谷', '百色市靖西市湖润镇新灵村', 115, 'AAAA', '2025-08-22 08:00:00', '计划徒步游览峡谷，需穿舒适鞋子，带饮用水', '账号8', '吴丹', '13877323458', '已支付', 38, 8.0, '景色不错', '已评价', NULL, NULL, 'bose_travel', '百色靖西文旅管理公司');
INSERT INTO `ticketreserve` VALUES (1766479694978, '2025-12-23 16:48:14', '漓江景区', '桂林市阳朔县阳朔镇抗战路6号', 210, 'AAAAA', '2025-12-23 16:48:13', '', '1', '张三', '18154541454', '未支付', 31, 0.0, NULL, '待审核', NULL, 'upload/1678358274781.jpg', 'nanning_travel', '南宁文旅集团有限公司');
INSERT INTO `ticketreserve` VALUES (1766479697964, '2025-12-23 16:48:17', '漓江景区', '桂林市阳朔县阳朔镇抗战路6号', 210, 'AAAAA', '2025-12-23 16:48:16', '', '1', '张三', '18154541454', '未支付', 31, 0.0, NULL, '待审核', NULL, 'upload/1678358274781.jpg', 'nanning_travel', '南宁文旅集团有限公司');

-- ----------------------------
-- Table structure for token
-- ----------------------------
DROP TABLE IF EXISTS `token`;
CREATE TABLE `token`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `userid` bigint(0) NOT NULL COMMENT '用户id',
  `username` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '用户名',
  `tablename` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '表名',
  `role` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '角色',
  `token` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '密码',
  `addtime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '新增时间',
  `expiratedtime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '过期时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = 'token表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of token
-- ----------------------------

-- ----------------------------
-- Table structure for tourplan
-- ----------------------------
DROP TABLE IF EXISTS `tourplan`;
CREATE TABLE `tourplan`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `mudedi` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '目的地',
  `starttime` datetime(0) NULL DEFAULT NULL COMMENT '出发时间',
  `duration` int(0) NULL DEFAULT NULL COMMENT '预计天数',
  `route` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '旅游路线',
  `content` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '规划内容',
  `username` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '账号',
  `name` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '姓名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1678358426135 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '旅游规划' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tourplan
-- ----------------------------
INSERT INTO `tourplan` VALUES (111, '2025-12-09 18:32:16', '桂林+阳朔', '2025-08-06 08:00:00', 3, 'D1：抵达桂林→象鼻山→两江四湖→椿记烧鹅晚餐→夜游两江四湖；D2：漓江竹筏（杨堤→兴坪）→十里画廊→阳朔西街夜游；D3：遇龙河漂流→银子岩→返回桂林', 'D1住宿推荐漓江大瀑布饭店，体验江景房；D2漓江竹筏建议选金龙号游船；阳朔住西街附近民宿，可免二次入园门票；推荐品尝桂林米粉、阳朔啤酒鱼、田螺酿', '账号1', '张三');
INSERT INTO `tourplan` VALUES (112, '2025-12-09 18:32:16', '北海银滩+涠洲岛', '2025-08-12 08:00:00', 2, 'D1：北海银滩→侨港风情街→乘船赴涠洲岛→住涠洲岛；D2：涠洲岛鳄鱼山→天主教堂→石螺口海滩→返程北海', '需提前预订船票与海岛民宿，涠洲岛船票150元/人；建议携带防晒用品；北海推荐品尝海鲜大餐、沙虫粥，现点现做', '账号2', '李四');
INSERT INTO `tourplan` VALUES (113, '2025-12-09 18:32:16', '龙脊梯田深度游', '2025-08-18 08:00:00', 3, 'D1：抵达龙胜→平安寨梯田（09:30-11:30）→龙脊镇午餐→金坑大寨梯田（14:00-15:30）→住大寨；D2：大寨日出→千层天梯（09:00-11:00）→黄洛瑶寨长发村（14:00-15:30）→住平安寨；D3：平安寨七星伴月→返程桂林', '龙脊梯田门票100元/人，需导游讲解更有意境；金坑大寨梯田秋季稻谷金黄，拍照效果佳；黄洛瑶寨需看瑶族歌舞表演；大寨晚餐推荐竹筒饭、酸肉，人均60元；景区间交通可租景区直通车（40元/天）', '账号3', '王五');
INSERT INTO `tourplan` VALUES (114, '2025-12-09 18:32:16', '南宁亲子游', '2025-08-22 08:00:00', 4, 'D1：抵达南宁→南宁动物园（09:30-12:00，熊猫馆+海豚表演）→中山路美食街午餐→广西科技馆（14:30-16:30，免费，儿童互动展区）→住市区；D2：青秀山（09:00-11:00，儿童乐园）→广西民族博物馆（14:00-15:30，民族服饰体验）→住市区；D3：方特东盟神画（09:00-17:00，亲子互动表演）→住市区；D4：南宁海底世界（10:00-14:00，美人鱼表演）→返程', '南宁动物园50元/人，儿童游乐区有过山车、旋转木马；广西科技馆周二至周日开放，需提前预约；青秀山门票20元/人，电瓶车50元/人；方特东盟神画有儿童喜欢的《伴你飞翔》表演；海底世界门票120元/人，美人鱼表演时间为11:00和15:00，提前占位', '账号4', '赵六');
INSERT INTO `tourplan` VALUES (115, '2025-12-09 18:32:16', '柳州文化休闲游', '2025-08-25 08:00:00', 3, 'D1：抵达柳州→螺蛳粉小镇（09:30-11:30，螺蛳粉制作体验）→青云民生市场午餐→龙潭公园（14:00-15:30，侗族风雨桥）→住市区；D2：程阳八寨（09:00-11:00，侗族百家宴）→三江鼓楼（14:00-15:30，侗族歌舞）→住市区；D3：马鞍山（09:00-11:00，俯瞰柳州全景）→窑埠古镇（14:00-16:00，文创购物）→返程', '螺蛳粉小镇免费，需预约，可体验螺蛳粉制作；程阳八寨门票100元/人，百家宴50元/人；龙潭公园免费，风雨桥拍照极佳；窑埠古镇有文创店铺，可买侗族饰品；马鞍山缆车30元/人，夜景绝美', '账号5', '孙七');
INSERT INTO `tourplan` VALUES (116, '2025-12-09 18:32:16', '巴马长寿村+凤山避暑游', '2025-08-28 08:00:00', 3, 'D1：南宁→巴马长寿村（08:00-11:00，乘旅游大巴）→长寿村民宿入住→百魔洞（14:00-15:30）→长寿村晚餐→养生讲座（20:00-21:30）；D2：巴马长寿村日出（05:30-06:30）→盘阳河漂流（09:00-11:30）→民宿午餐→凤山三门海（14:00-15:30，乘船游览）→住凤山；D3：凤山鸳鸯洞（09:00-10:00）→返程南宁（11:00-12:30）', '巴马长寿村民宿选长寿老人附近，夏季气温22-28℃，需带薄外套；百魔洞门票80元/人，地磁区适合养生；盘阳河漂流120元/人，水质清澈；三门海乘船60元/人，可看天窗奇观；凤山夏季避暑胜地，平均气温25℃', '账号6', '周八');
INSERT INTO `tourplan` VALUES (117, '2025-12-09 18:32:16', '崇左短线休闲游', '2025-09-01 08:00:00', 2, 'D1：抵达崇左→德天瀑布（09:30-10:30）→中越边境公路（11:00-12:00）→明仕田园午餐→明仕田园竹筏（14:00-15:30）→住明仕；D2：花山岩画（09:00-11:00，小游船）→友谊关午餐→返程崇左', '德天瀑布门票80元/人，观光车35元/人；明仕田园竹筏120元/人，《花千骨》取景地；花山岩画游船190元/人，世界文化遗产；友谊关门票50元/人，中越边境地标；适合时间紧张的游客，行程轻松不赶', '账号7', '吴九');
INSERT INTO `tourplan` VALUES (118, '2025-12-09 18:32:16', '涠洲岛+北海老街游', '2025-09-05 08:00:00', 2, 'D1：抵达北海→北海老街（09:30-11:00）→侨港海滩午餐→乘船赴涠洲岛（14:00-17:00，鳄鱼山）→涠洲岛夜景→住涠洲岛；D2：涠洲岛→天主教堂（09:00-11:00）→贝壳沙滩午餐→滴水丹屏（14:00-15:00）→返程北海', '北海老街免费，百年骑楼建筑；涠洲岛船票+门票240元/人；天主教堂门票10元/人，法式建筑特色鲜明；贝壳沙滩适合赶海；滴水丹屏日落绝美；推荐品尝海鲜烧烤、糖水', '账号8', '郑十');
INSERT INTO `tourplan` VALUES (1678358426134, '2025-12-09 18:40:26', '桂林+阳朔3日家庭游', '2025-07-30 08:00:00', 5, 'D1：抵达桂林→住桂林漓江大瀑布饭店→象鼻山（10:00-11:30）→酒店午餐→东西巷（15:00-16:30）→阿甘酒家晚餐；D2：桂林→阳朔（09:00-11:30，自驾）→漓江游船（14:00-17:30）→住阳朔；D3：遇龙河漂流（10:00-13:00）→阳朔西街晚餐；D4：阳朔→桂林（09:00-11:30）→融创乐园（14:00-17:00）→住桂林；D5：桂林两江四湖（09:00-14:00）→返程', '<p>家庭游行程宽松，每天只安排2-3个景点，避免孩子疲劳；D1酒店选江景房，孩子喜欢湖边散步；D2漓江游船选家庭座位，空间大；阳朔推荐品尝啤酒鱼，适合儿童口味；D3融创乐园有儿童互动环节，孩子参与度高；D4两江四湖可乘船游览，景色优美；D5返程前可购买桂花糕作为手信</p>', '111', '张三');

-- ----------------------------
-- Table structure for trafficroute
-- ----------------------------
DROP TABLE IF EXISTS `trafficroute`;
CREATE TABLE `trafficroute`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `trafficname` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '路线名称',
  `traffictype` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '路线类型',
  `origin` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '始发地',
  `destination` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '终点地',
  `distance` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '全程距离',
  `trafficcontent` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '路线详情',
  `trafficphoto` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '路线图片',
  `content` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '路线详情',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 96 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '交通路线' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of trafficroute
-- ----------------------------
INSERT INTO `trafficroute` VALUES (91, '2025-12-09 18:32:15', '南宁东站→桂林漓江景区公交专线', '公交专线', '南宁东站', '桂林漓江景区（磨盘山码头）', '约400公里', '南宁东站出站后乘坐桂林旅游专线大巴，票价120元/人，儿童1.2米以下免票。运营时间7:00-18:00，每2小时1班，车程约4小时30分钟。抵达漓江磨盘山码头，步行5分钟至售票大厅', 'upload/traffic1.jpg,upload/traffic2.jpg,upload/traffic3.jpg', '专线大巴直达景区，适合无行李游客；支持支付宝“广西交通码”');
INSERT INTO `trafficroute` VALUES (92, '2025-12-09 18:32:15', '桂林景区内观光车路线', '景区观光车', '桂林南溪山公园', '各景点接驳', '约30公里', '景区内实行观光车接驳，通票50元/人（2日有效）。主要路线：南溪山→象鼻山→七星公园→两江四湖→漓江码头。运营时间7:00-18:00，每20分钟一班，凭票可无限次乘坐，建议按“象鼻山→七星公园→漓江”顺序游览', 'upload/traffic2.jpg,upload/traffic3.jpg,upload/traffic4.jpg', '景区内必乘交通，节省体力，班次密集无需等待');
INSERT INTO `trafficroute` VALUES (93, '2025-12-09 18:32:15', '南宁市区→德天瀑布景区公交路线', '景区公交', '南宁琅东汽车站', '德天瀑布游客中心', '约200公里', '1. 乘车点与班次：乘车点为南宁琅东汽车站，乘坐崇左旅游专线，每日6:30-17:30，每1小时1班，票价80元/人，支持支付宝“广西交通码”。\r\n2. 行程细节：全程走合那高速，车程约3小时，沿途经喀斯特地貌风光；大巴配备语音讲解，介绍沿途景点；可在“德天瀑布站”下车，步行5分钟至景区入口。\r\n3. 接驳提示：景区内观光车35元/人，必须乘坐；返程末班车为17:00，建议提前规划时间', 'upload/traffic4.jpg,upload/traffic5.jpg,upload/traffic6.jpg', '公交直达景区门口，适合无行李游客；沿途可观赏喀斯特峰林');
INSERT INTO `trafficroute` VALUES (94, '2025-12-09 18:32:15', '桂林→阳朔直通车路线', '景区直通车', '桂林火车站', '阳朔西街游客中心', '约70公里', '1. 路线规划：从桂林火车站出发，乘坐阳朔直通车直达阳朔西街，每日一班（08:00-18:00），票价30元/人，车程约1小时30分钟，全程高速。\r\n2. 乘车准备：可在桂林游客中心或线上平台提前1天购票，支持微信/支付宝支付；车内有空调和行李存放区，适合携带行李游客；夏季需携带防晒用品。\r\n3. 沿途亮点：直通车中途在十里画廊停靠10分钟拍照；抵达后可直接在游客中心租用自行车（20元/天）；阳朔西街可步行游览，主要景点集中在西街周边', 'upload/traffic5.jpg,upload/traffic6.jpg,upload/traffic7.jpg', '直通车适合串联两大景点游览；避免自驾疲劳，性价比高');
INSERT INTO `trafficroute` VALUES (95, '2025-12-09 18:32:15', '南宁市区→巴马长寿村自驾路线', '自驾', '南宁青秀区', '河池巴马长寿村', '约250公里', '1. 路线导航：从南宁青秀区出发，经兰海高速→巴马出口，全程约250公里，车程3小时20分钟；导航直接搜索“巴马长寿村游客中心”，停车场免费。\r\n2. 路况提示：全程高速为主，巴马出口后为二级公路，路况良好；沿途有多处观景台，可短暂停车休息；雨天需减速慢行。\r\n3. 配套服务：抵达后可在游客中心租用电动车（30元/天）游览长寿村；村里有多家养生菜馆，推荐巴马香猪；返程可购买当地火麻油、巴马矿泉水等特产', 'upload/traffic6.jpg,upload/traffic7.jpg,upload/traffic8.jpg', '自驾适合家庭或多人出行，灵活性高；沿途可随时停车拍照；避开节假日高峰更顺畅');

-- ----------------------------
-- Table structure for travelguide
-- ----------------------------
DROP TABLE IF EXISTS `travelguide`;
CREATE TABLE `travelguide`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `title` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '标题',
  `address` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '旅行地点',
  `traveltype` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '旅行类型',
  `duration` int(0) NULL DEFAULT NULL COMMENT '旅行天数',
  `introduction` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '旅行内容',
  `travelphoto` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '旅行图片',
  `username` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '账号',
  `name` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `thumbsupnum` int(0) NULL DEFAULT 0 COMMENT '赞',
  `crazilynum` int(0) NULL DEFAULT 0 COMMENT '踩',
  `content` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '简介',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1678358413823 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '旅游攻略' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of travelguide
-- ----------------------------
INSERT INTO `travelguide` VALUES (101, '2025-12-09 18:32:15', '桂林漓江深度游：3天2晚解锁山水奇观', '广西桂林漓江', '山水风光', 3, '第一天：象鼻山→两江四湖→夜游漓江。第二天：漓江竹筏（杨堤→兴坪）→十里画廊→遇龙河漂流。第三天：银子岩→返程。含竹筏提前3天预订技巧，避开人流的最佳游览时段。', 'upload/guide1.jpg,upload/guide2.jpg,upload/guide3.jpg', 'oldtown_explorer', '李梦', 128, 5, '涵盖桂林核心景点，附桂花糕购买指南，适合首次到访游客');
INSERT INTO `travelguide` VALUES (102, '2025-12-09 18:32:15', '广西壮乡文化4日研学：从南宁到百色', '广西南宁、百色', '民族风情', 4, '第一天：南宁广西民族博物馆→青秀山民族图腾。第二天：百色靖西鹅泉→旧州绣球村。第三天：巴马长寿村→民族养生文化。第四天：返程。含民族三月三活动预约方式，适合带孩子的家庭。', 'upload/guide2.jpg,upload/guide3.jpg,upload/guide4.jpg', 'river_mountain', '王浩', 96, 3, '系统了解民族文化脉络，附各博物馆讲解预约电话');
INSERT INTO `travelguide` VALUES (103, '2025-12-09 18:32:15', '北海涠洲岛探秘3日：海岛+火山奇观', '广西北海涠洲岛', '滨海度假', 3, '第一天：抵达北海→北海银滩→乘船赴涠洲岛→鳄鱼山火山公园。第二天：涠洲岛天主教堂→贝壳沙滩赶海→滴水丹屏日落。第三天：涠洲岛→北海老街→返程。含船票预订指南，住宿推荐海景民宿。', 'upload/guide3.jpg,upload/guide4.jpg,upload/guide5.jpg', 'mountain_climber', '张伟', 215, 8, '感受北海海岛风情，附海鲜购买防坑指南，避开旅游陷阱');
INSERT INTO `travelguide` VALUES (104, '2025-12-09 18:32:15', '巴马长寿村3日：养生+探秘之旅', '广西河池巴马', '长寿养生', 3, '第一天：南宁→巴马长寿村→百魔洞→长寿村住宿。第二天：盘阳河漂流→三门海天窗→养生讲座。第三天：凤山鸳鸯洞→返程。含养生食谱与住宿推荐，夏季避暑注意事项。', 'upload/guide4.jpg,upload/guide5.jpg,upload/guide6.jpg', 'island_time', '刘芳', 156, 4, '夏季平均气温26℃，适合中老年养生，附巴马长寿秘诀');
INSERT INTO `travelguide` VALUES (105, '2025-12-09 18:32:15', '广西美食3日寻味之旅', '广西各地', '美食体验', 3, '每日主题：第一天南宁（老友粉→中山路夜市）；第二天柳州（螺蛳粉→三江油茶）；第三天桂林（桂林米粉→阳朔啤酒鱼）。每天推荐5-8家必吃店铺，从早餐到夜宵全覆盖。', 'upload/guide5.jpg,upload/guide6.jpg,upload/guide7.jpg', '1', '张三', 289, 6, '本地吃货整理的真实推荐，附各店招牌菜和最佳用餐时间');
INSERT INTO `travelguide` VALUES (106, '2025-12-09 18:32:15', '广西自驾环线5日：山水与边关交融', '广西全域', '自驾旅游', 5, '行程涵盖：桂林→阳朔→南宁→崇左→德天瀑布→靖西→返程。特别安排民族民俗活动与边关文化体验，详细的每日车程与住宿推荐，适合家庭自驾。', 'upload/guide6.jpg,upload/guide7.jpg,upload/guide8.jpg', '1', '张三', 178, 7, '全程约800公里，路况良好，附各景点停车场信息与加油站点');
INSERT INTO `travelguide` VALUES (107, '2025-12-09 18:32:16', '德天瀑布边关风情2日体验', '广西崇左德天瀑布', '边关风情', 2, '详细介绍中越边境文化，包括德天瀑布游览、中越集市购物、民族侬峒节体验。推荐3家边境民宿体验预约，附边境旅游注意事项，适合文化爱好者。', 'upload/guide7.jpg,upload/guide8.jpg,upload/guide9.jpg', '1', '张三', 203, 5, '从瀑布到边关的全流程体验，附越南特产购买指南');
INSERT INTO `travelguide` VALUES (108, '2025-12-09 18:32:16', '广西徒步探险3日：喀斯特奇观探索', '广西乐业、凤山', '山水风光', 3, '<p>第一天：乐业大石围天坑徒步→穿洞天坑观景。第二天：凤山三门海→鸳鸯洞探险。第三天：巴马百魔洞→返程。含徒步装备清单与难度评级，适合有一定经验的徒步者。</p>', 'upload/guide8.jpg,upload/guide9.jpg,upload/guide10.jpg', '1', '张三', 145, 9, '广西喀斯特奇观徒步路线，附天气查询与安全提示');
INSERT INTO `travelguide` VALUES (1678358413822, '2025-12-09 18:40:13', '广西亲子游4日：玩乐与研学结合', '广西主要景区', '亲子游乐', 4, '<p>第一天：南宁动物园→广西科技馆。第二天：桂林融创乐园→漓江游船。第三天：阳朔遇龙河竹筏→蝴蝶泉。第四天：北海银滩→返程。含儿童优惠政策与体力分配建议。</p><p>可插入图片</p><p><img src=\"http://localhost:8080/inttourismsys/upload/1759911671640.jpg\"></p>', 'upload/1678358410427.jpeg', '1', '张三', 89, 2, '适合3-12岁儿童，附亲子互动游戏推荐，轻松无压力行程');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `username` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '账号',
  `password` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '密码',
  `name` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '姓名',
  `gender` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '性别',
  `age` int(0) NULL DEFAULT NULL COMMENT '年龄',
  `telephone` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '手机',
  `avatar` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '头像',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1678358276063 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '用户' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (11, '2025-12-09 18:32:15', '账号1', '123456', '李伟', '男', 32, '13877123451', 'upload/users1.jpg');
INSERT INTO `users` VALUES (12, '2025-12-09 18:32:15', '账号2', '123456', '陈芳', '女', 28, '13877323452', 'upload/users2.jpg');
INSERT INTO `users` VALUES (13, '2025-12-09 18:32:15', '账号3', '123456', '黄明', '男', 45, '13877223453', 'upload/users3.jpg');
INSERT INTO `users` VALUES (14, '2025-12-09 18:32:15', '账号4', '123456', '杨丽', '女', 36, '13877923454', 'upload/users4.jpg');
INSERT INTO `users` VALUES (15, '2025-12-09 18:32:15', '账号5', '123456', '赵强', '男', 29, '13877623455', 'upload/users5.jpg');
INSERT INTO `users` VALUES (16, '2025-12-09 18:32:15', '账号6', '123456', '韦丽', '女', 33, '13877823456', 'upload/users6.jpg');
INSERT INTO `users` VALUES (17, '2025-12-09 18:32:15', '账号7', '123456', '周健', '男', 41, '13877123457', 'upload/users7.jpg');
INSERT INTO `users` VALUES (18, '2025-12-09 18:32:15', '账号8', '123456', '吴丹', '女', 27, '13877323458', 'upload/users8.jpg');
INSERT INTO `users` VALUES (1678358276062, '2025-12-09 18:37:56', '1', '1', '张三', '女', 25, '18154541454', 'upload/1678358274781.jpg');

-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
