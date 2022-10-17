import 'package:flutter/material.dart';

class UploadfoodPage extends StatelessWidget {
  const UploadfoodPage({Key? key}) : super(key:key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("음식등록"),
          centerTitle: true,
      ),
    );
  }
}