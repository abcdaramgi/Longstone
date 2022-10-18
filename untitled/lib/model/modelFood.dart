class Food{
  final String poster;
  final String name;
  final String store;
  final String info;
  final String time;
  final String cost;
  final String score;
  final String location;

  Food.fromMap(Map<String, dynamic> map)
      : poster = map['poster'],
        name = map['name'],
        store = map['store'],
        info = map['info'],
        time = map['time'],
        score = map['score'],
        location = map['location'],
        cost = map['cost'];

  @override
  String toString() => "Food<$name:<$store>>";
}