该项目为本科实训课程项目，课题名称为仿写“今日头条”APP。我作为组长，带领4名团队成员拿下专业第一的好成绩。该项目于2018年7月开发完毕，开发时使用Gitee。于2022年2月由Gitee迁移至GitHub。
***

最理想状态的数据流向.png![image](https://user-images.githubusercontent.com/24218765/154842642-bd3076a5-6515-4846-8223-c1cc2f0d8bde.png)


周四晚上安装包里必须包含release签名

周四晚上发送答辩资料也可以通过qq发送

1.在loginActivity与loginFragment通信的过程中，使用了回调

**布局层**

第三方控件
1. 动画库AVLoadingIndicatorView
2. 侧滑删除/收藏 SwipeDelMenuLayout
3. TopSnackBar

尝试新控件（v4包/v7包/MaterialDesign）
1. CoordinatorLayout
2. RecyclerView
3. FloatingActionButton
4. NestedScrollView
5. AppBarLayout
6. BottomNavigationView 底部导航栏
7. ViewPager

自定义控件

1. NoSlidingViewPaper extends ViewPager 设置事件为不传递

Vector Drawable - Android中的SVG实现


**数据持久层**

1. DiskLruCache
2. 基于Sqlite的Litepal
3. 缓存图片时MD5加密避免了URL中可能含有非法字符问题，一定程度上对用户隐藏实现
