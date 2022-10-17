import 'package:flutter/material.dart';
import 'upload_food.dart';
import 'Home/home.dart';
import 'contol.dart';

void main(){
  runApp(const MyApp());
}

class MyApp extends StatelessWidget{
  const MyApp({super.key});

  @override
  Widget build(BuildContext context){
    return MaterialApp(
      title: 'GoldenHour',
      theme: ThemeData(
          scaffoldBackgroundColor: Colors.white
      ),
      home: const MainPage(),
    );
  }
}

class MainPage extends StatefulWidget {
  const MainPage({super.key});

  @override
  _MainPageState createState() => _MainPageState();
}

class _MainPageState extends State<MainPage> {
  final List<Widget> _widgetOptions = <Widget>[
    const UploadfoodPage(),
    HomePage(),
    const contolPage(),
  ];

  int _selectionIndex = 1;

  void _onItemTapped(int index) {
    setState(() {
      _selectionIndex = index;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: _widgetOptions[_selectionIndex],
      bottomNavigationBar: BottomNavigationBar(
        items: const <BottomNavigationBarItem>[
          BottomNavigationBarItem(
              icon: Icon(Icons.add_circle_rounded), label: '등록',),
          BottomNavigationBarItem(
            icon: Icon(Icons.home), label: '홈',),
          BottomNavigationBarItem(
              icon: Icon(Icons.person), label: '설정'),
        ],
        currentIndex: _selectionIndex,
        selectedItemColor: Colors.redAccent,
        onTap: _onItemTapped,
      ),
    );
  }
}