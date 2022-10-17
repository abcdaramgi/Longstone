import 'package:flutter/material.dart';
import 'package:goldenhour/Screen/detail_screen.dart';
import 'package:goldenhour/model/model_food.dart';

class BoxSlider extends StatelessWidget{
  final List<Food> foods;
  BoxSlider({required this.foods});

  @override
  Widget build(BuildContext context){
    return Container(
      padding: EdgeInsets.all(7),
      child: Column(
        //crossAxisAlignment: CrossAxisAlignment.start,
        children: <Widget> [
          //Text('오늘 올라온 음식'),
          Container(
            height: 600,
            child: ListView(
              scrollDirection: Axis.vertical,
              children: makeBoxImages(context, foods),
            ),
          )
        ],
      ),
    );
  }

  List<Widget> makeBoxImages(BuildContext context, List<Food> foods){
    List<Widget> results = [];
    for(var i=0; i<foods.length;i++){
      results.add(
          InkWell(
          onTap:(){
            Navigator.of(context).push(MaterialPageRoute<Null>(
                fullscreenDialog: true,
                builder: (BuildContext context){
                  return DetailScreen(
                      food: foods[i],
                  );
            }));
          },
        child: Container(
          padding: EdgeInsets.only(bottom: 10),
          child: Align(
            alignment: Alignment.bottomCenter,
            child: Row(
              children: [
                Image.asset('images/'+foods[i].poster, width: 120,),
                Expanded(
                  child: Container(
                    padding: EdgeInsets.all(10),
                    child: Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Container(
                          child: Text(foods[i].name,
                            style: TextStyle(
                              fontSize: 20,
                              fontWeight: FontWeight.bold
                            ),
                          ),
                        ),
                        Container(
                          margin: EdgeInsets.only(top: 3),
                          child: Text(foods[i].store),
                        ),
                        Container(
                          margin: EdgeInsets.only(top: 10),
                          child: Text(foods[i].cost,
                            style: TextStyle(
                                fontSize: 15,
                                fontWeight: FontWeight.bold
                            ),
                          ),
                        ),
                      ],
                    ),
                  ),
                )
              ],
            ),
          ),
        ),
      ));
    }
    return results;
  }
}