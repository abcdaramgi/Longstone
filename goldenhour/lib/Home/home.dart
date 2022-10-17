import 'package:flutter/material.dart';
import 'package:goldenhour/foodbell.dart';
import 'package:goldenhour/model/model_food.dart';
import '../info_store.dart';
import 'Search.dart';
import 'DrawerBar.dart';
import 'foodlist.dart';

class HomePage extends StatefulWidget{
  const HomePage({super.key});

  @override
  _HomePageState createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  final GlobalKey<ScaffoldState> _scaffoldKey = GlobalKey();
  List<Food> foods = [
    Food.fromMap({
      'poster':'bread.png',
      'name' : '소금빵',
      'store' : '빵집',
      'info' : '10년 간의 전통으로 만든 소금빵\n 오늘 5개 남아서 싼 가격에 넘겨요~!\n',
      'time' : '2시간 뒤 마감',
      'location' : '대구 북구 구암동',
      'score' : '별점 : 5.0점',
      'cost' : '1개:3,000원->1,300원'
    }),
    Food.fromMap({
      'poster':'붕어빵.jpeg',
      'name' : '붕어빵',
      'store' : '수이니네',
      'info' : '오늘 따라 맛있는 붕어빵.\n 다들 먹고 기절할껄 ?\n',
      'time' : '1시간 뒤 마감',
      'location' : '대구 북구 동천동',
      'score' : '별점 : 5.0점',
      'cost' : '5개:2,500원->1,000원'
    }),
    Food.fromMap({
      'poster':'bread.png',
      'name' : '소금빵',
      'store' : '감자네 빵집',
      'info' : '10년 간의 전통으로 만든 소금빵\n 오늘 5개 남아서 싼 가격에 넘겨요~!\n',
      'time' : '2시간 뒤 마감',
      'location' : '대구 북구 동천동',
      'score' : '별점 : 4.7점',
      'cost' : '1개:2,800원->1,000원'
    }),
    Food.fromMap({
      'poster':'kimch.jpeg',
      'name' : '김치찌개',
      'store' : '맘스토치',
      'info' : '10년 간의 전통으로 만든 김치찌개\n',
      'time' : '2시간 뒤 마감',
      'location' : '대구 북구 태전동',
      'score' : '별점 : 4.9점',
      'cost' : '1인분:9,000원->5,500원'
    }),
    Food.fromMap({
      'poster':'bread.png',
      'name' : '소금빵',
      'store' : '감자네 빵집',
      'info' : '10년 간의 전통으로 만든 소금빵\n 오늘 5개 남아서 싼 가격에 넘겨요~!\n',
      'time' : '2시간 뒤 마감',
      'location' : '대구 북구 구암동',
      'score' : '별점 : 5.0점',
      'cost' : '1개:2,800원->1,000원'
    })
  ];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        key: _scaffoldKey,
        appBar: AppBar(
          title: const Text('오늘의 음식', style: TextStyle(color: Colors.black),),
          // 가운데 정렬
          centerTitle: true,
          backgroundColor: Colors.white,
          // appBar 밑 그림자 제거,
          elevation: 0.0,
          leading: const IconButton(
            icon: Icon(Icons.arrow_back_ios),
            onPressed: null,),
          actions: <Widget> [
            IconButton(icon:Icon(Icons.notifications),
                onPressed: () {
                  Navigator.push(
                      context, MaterialPageRoute(builder: (_) => const BellPage()));
                }),
            IconButton(icon: const Icon(Icons.menu_sharp), color: Colors.grey, onPressed: () {
              _scaffoldKey.currentState?.openEndDrawer();
            }),
          ],
        ),
        endDrawer: Drawer(
          child: DrawerBar(),
        ),
        body: ListView(
          children: <Widget>[
            SearchScreen(),
            BoxSlider(foods: foods),
          ],
        ),
    );
  }
}