import 'package:flutter/material.dart';

import '../info_store.dart';

class DrawerBar extends StatelessWidget {
  const DrawerBar({super.key});

  @override
  Widget build(BuildContext context) {
    return ListView(
            children : <Widget> [
              ListTile(
                leading: const Icon(
                    Icons.arrow_back_ios
                ),
                title: const Text("등록관리"),
                onTap: () {
                  Navigator.push(
                      context, MaterialPageRoute(builder: (_) => const InfoStore()));
                },
              ),
              ListTile(
                leading: const Icon(
                    Icons.arrow_back_ios
                ),
                title: const Text("리뷰관리"),
                onTap: () {
                  Navigator.push(
                      context, MaterialPageRoute(builder: (_) => const InfoStore()));
                },
              ),
              ListTile(
                leading: const Icon(
                    Icons.arrow_back_ios
                ),
                title: const Text("가게정보"),
                onTap: () {
                  Navigator.push(
                      context, MaterialPageRoute(builder: (_) => const InfoStore()));
                },
              ),
              ListTile(
                leading: const Icon(
                    Icons.arrow_back_ios
                ),
                title: const Text("공지사항"),
                onTap: () {
                  Navigator.push(
                      context, MaterialPageRoute(builder: (_) => const InfoStore()));
                },
              ),
              ListTile(
                leading: const Icon(
                    Icons.arrow_back_ios
                ),
                title: const Text("환경설정"),
                onTap: () {
                  Navigator.push(
                      context, MaterialPageRoute(builder: (_) => const InfoStore()));
                },
              )
            ]);
  }
}