# TodaysHeadlineTraining
该项目为本科实训课程项目，课题名称为仿写“今日头条”APP。我作为组长，带领4名团队成员拿下专业第一的好成绩。该项目于2018年7月开发完毕，开发时使用Gitee。于2022年2月由Gitee迁移至GitHub。
把答辩PPT贴在这里以介绍项目。
***
## 答辩PPT
![幻灯片1](https://user-images.githubusercontent.com/24218765/154842856-5bc723f6-e94f-4fec-a12b-60ead0fc871a.jpeg)
![幻灯片2](https://user-images.githubusercontent.com/24218765/154842862-6063c054-770a-4da5-9ac2-bdb9c6dded0e.jpeg)
![幻灯片3](https://user-images.githubusercontent.com/24218765/154842866-701a575c-a6ea-4fdf-a88d-44dd7453e63a.jpeg)
![幻灯片4](https://user-images.githubusercontent.com/24218765/154842874-8aafddb9-0dfb-4792-a522-9a1fef663204.jpeg)
![幻灯片5](https://user-images.githubusercontent.com/24218765/154842875-443be255-5baf-4285-b88f-8efb82d1e8d0.jpeg)
![幻灯片6](https://user-images.githubusercontent.com/24218765/154842889-616cd1a2-f665-4bd5-a27e-5e4730605dab.jpeg)
![幻灯片7](https://user-images.githubusercontent.com/24218765/154842894-844e0ccf-e492-4f28-a06e-79536fdcaf3d.jpeg)
![幻灯片8](https://user-images.githubusercontent.com/24218765/154842896-5af4d8b5-9040-44c4-a99a-d968f1f7033e.jpeg)
![幻灯片9](https://user-images.githubusercontent.com/24218765/154842897-7f787cc1-676b-48dd-91b3-60b13116c220.jpeg)
![幻灯片10](https://user-images.githubusercontent.com/24218765/154842906-1b87ce32-674f-46b5-8678-ee8b4612dd3f.jpeg)
![幻灯片11](https://user-images.githubusercontent.com/24218765/154842911-40ed0ef6-60f8-4b38-9b68-2e6916e5915e.jpeg)
![幻灯片12](https://user-images.githubusercontent.com/24218765/154842913-a3373f23-cc1a-4b35-92ea-2b8e7f9f8b76.jpeg)
![幻灯片13](https://user-images.githubusercontent.com/24218765/154842915-e3146427-fc2a-48d4-87b2-393e75708f6d.jpeg)
![幻灯片14](https://user-images.githubusercontent.com/24218765/154842917-41f3a4be-6db4-47c1-8e1c-8108318c9e67.jpeg)
![幻灯片15](https://user-images.githubusercontent.com/24218765/154842918-0c509b7e-1dee-4496-b0c2-07d7d79ad31e.jpeg)
![幻灯片16](https://user-images.githubusercontent.com/24218765/154842921-1b190a78-5870-4027-9e11-8188b992ed98.jpeg)
![幻灯片17](https://user-images.githubusercontent.com/24218765/154842933-1b70211a-d4e1-432b-a80f-d3d0489d8657.jpeg)
![幻灯片18](https://user-images.githubusercontent.com/24218765/154842936-733c979d-3734-49f1-8357-3c443426e83d.jpeg)
![幻灯片19](https://user-images.githubusercontent.com/24218765/154842938-53035eaa-85df-4994-9113-9f0e92632a1d.jpeg)
最理想状态的数据流向.png![image](https://user-images.githubusercontent.com/24218765/154842642-bd3076a5-6515-4846-8223-c1cc2f0d8bde.png)
![幻灯片20](https://user-images.githubusercontent.com/24218765/154842940-62928922-53e2-4b42-b716-31baf909496d.jpeg)
![幻灯片21](https://user-images.githubusercontent.com/24218765/154842941-fcf80d3b-28d9-4c89-b020-c4ef55fa0001.jpeg)
![幻灯片22](https://user-images.githubusercontent.com/24218765/154842945-92291127-b5b9-4b7a-acd7-fe1411dffc9a.jpeg)
![幻灯片23](https://user-images.githubusercontent.com/24218765/154842958-43921bd8-2506-4da7-bd0c-bf3267aa2125.jpeg)

## 项目亮点
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
