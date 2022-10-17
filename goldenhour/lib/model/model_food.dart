class Food{
  final String poster;
  final String name;
  final String store;
  final String info;
  final String time;
  final String cost;

  Food.fromMap(Map<String, dynamic> map)
      : poster = map['poster'],
        name = map['name'],
        store = map['store'],
        info = map['info'],
        time = map['time'],
        cost = map['cost'];

  @override
  String toString() => "Food<$name:<$store>>";
}