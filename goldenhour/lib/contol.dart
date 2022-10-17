import 'package:flutter/material.dart';

class contolPage extends StatelessWidget {
  const contolPage({Key? key}) : super(key:key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("환경설정"),
          centerTitle: true,
      ),
    );
  }
}