import 'package:flutter/material.dart';

class InfoStore extends StatelessWidget {
  const InfoStore({Key? key}) : super(key:key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: const Text('가게정보', style: TextStyle(color: Colors.black),),
          // 가운데 정렬
          centerTitle: true,
          backgroundColor: Colors.white,
          // appBar 밑 그림자 제거,
          elevation: 0.0,
        )
    );
  }
}