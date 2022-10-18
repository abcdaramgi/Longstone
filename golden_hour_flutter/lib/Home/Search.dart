import 'package:flutter/material.dart';

class SearchScreen extends StatefulWidget{
  const SearchScreen({super.key});

  @override
  _SearchScreenState createState() => _SearchScreenState();
}

class _SearchScreenState extends State<SearchScreen>{
  final TextEditingController _filter = TextEditingController();
  FocusNode focusNode = FocusNode();
  String _searchText = "";

  _SearchScreenState(){
    _filter.addListener(() {
      setState(() {
        _searchText = _filter.text;
      });
    });
  }

  @override
  Widget build(BuildContext context){
    return Column(
      children: <Widget>[
        Container(
            color: Colors.white,
            padding: EdgeInsets.fromLTRB(5, 10, 5, 10),
            child: Row(
              children : <Widget>[
                Expanded(
                    flex: 6,
                    child: TextField(
                        focusNode: focusNode,
                        style: TextStyle(
                            fontSize: 15
                        ),
                        autofocus: true,
                        controller: _filter,
                        decoration: InputDecoration(
                          contentPadding: EdgeInsets.all(10),
                          filled: true,
                          fillColor: Colors.grey[300],
                          prefixIcon: Icon(
                            Icons.search,
                            color: Colors.black,
                            size: 20,
                          ),
                          suffixIcon: focusNode.hasFocus
                              ?IconButton(
                            icon: Icon(
                              Icons.cancel,
                              size: 20,
                            ),
                            onPressed: (){
                              setState((){
                                _filter.clear();
                                _searchText="";
                              });
                            },
                          )
                              :Container(),
                          hintText: '검색',
                          labelStyle: TextStyle(color: Colors.black45),
                          // 검색창 밑줄 없애고 투명하게 만드는 기능
                          focusedBorder: OutlineInputBorder(
                            borderSide: BorderSide(color: Colors.transparent),
                            borderRadius: BorderRadius.all(Radius.circular(10)),
                          ),
                          enabledBorder: OutlineInputBorder(
                            borderSide: BorderSide(color: Colors.transparent),
                            borderRadius: BorderRadius.all(Radius.circular(10)),
                          ),
                          border: OutlineInputBorder(
                              borderSide: BorderSide(color: Colors.transparent),
                              borderRadius: BorderRadius.all(Radius.circular(10))
                          ),
                        )
                    )
                )
              ],
            )
        )
      ],
    );
  }
}