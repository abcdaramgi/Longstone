import 'package:flutter/material.dart';
import 'package:goldenhour/model/model_food.dart';
import 'dart:ui';
import 'package:goldenhour/main.dart';

class DetailScreen extends StatefulWidget{
  final Food food;
  DetailScreen({required this.food});
  _DetailScreenState createState() => _DetailScreenState();
}

class _DetailScreenState extends State<DetailScreen>{
  @override
  Widget build(BuildContext context){
    return Scaffold(
      appBar: AppBar(
        title: const Text('오늘의 음식', style: TextStyle(color: Colors.black),),
        // 가운데 정렬
        centerTitle: true,
        backgroundColor: Colors.white,
        // appBar 밑 그림자 제거,
        elevation: 0.0,
        leading: IconButton(
          icon: Icon(Icons.arrow_back_ios, color: Colors.grey),
          onPressed: () {
            FocusScope.of(context).unfocus();
            Navigator.push(
                context, MaterialPageRoute(builder: (_) => const MainPage()));
            },),
      ),
      body: SafeArea(
          child: ListView(
            children: <Widget>[
              Column(
                children: <Widget>[
                  Container(
                    width: double.maxFinite,
                    height: 320,
                    child: Image.asset(
                        'images/'+widget.food.poster, fit: BoxFit.fitWidth,),
                  ),
                  Container(
                      width: double.maxFinite,
                    height: 80,
                    child: const DecoratedBox(
                    decoration: BoxDecoration(
                      border: Border(
                        bottom: BorderSide(
                          color: Colors.black12,
                          width: 1.0,
                        ),
                      ),
                      color: Colors.white,                      )
                    ),
                  ),
                  Container(
                    padding: EdgeInsets.only(top: 7,left: 15),
                    alignment: Alignment.centerLeft,
                    child: Text(
                      widget.food.name,
                      style: TextStyle(
                        fontWeight: FontWeight.bold,
                        fontSize: 30,
                        color: Colors.black,
                      ),
                    ),
                  ),
                  Container(
                    padding: EdgeInsets.only(top: 2,left: 15),
                    alignment: Alignment.centerLeft,
                    child: Text(
                      widget.food.score,
                      style: TextStyle(
                        fontWeight: FontWeight.bold,
                        fontSize: 13,
                        color: Colors.black,
                      ),
                    ),
                  ),
                  Container(
                    padding: EdgeInsets.only(top: 10,left: 15),
                    alignment: Alignment.centerLeft,
                    child: Text(
                      widget.food.info,
                      style: TextStyle(
                        fontWeight: FontWeight.bold,
                        fontSize: 15,
                        color: Colors.black,
                      ),
                    ),
                  ),
                ],
              )
            ],
          ),
        ),
    );
  }
}