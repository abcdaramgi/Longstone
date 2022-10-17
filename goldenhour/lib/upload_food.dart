import 'package:flutter/material.dart';

class UploadfoodPage extends StatelessWidget {
  const UploadfoodPage({Key? key}) : super(key:key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('음식등록', style: TextStyle(color: Colors.black),),
        // 가운데 정렬
        centerTitle: true,
        backgroundColor: Colors.white,
        // appBar 밑 그림자 제거,
        elevation: 0.0,
      )
    );
  }
}