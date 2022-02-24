import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: MyHomePage(title: 'Flutter Demo Home Page'),
    );
  }
}

MethodChannel platform = MethodChannel('methodChannel');

class EventChannelTutorial {

  static Stream<dynamic> get getRandomNumberStream {
    return const EventChannel('eventChannel').receiveBroadcastStream();
  }
}

class MyHomePage extends StatefulWidget {
  MyHomePage({Key? key, required this.title}) : super(key: key);

  final String title;

  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class MainState {}

class StateBoi extends MainState {
  final String message;

  StateBoi(this.message);
}

abstract class EventBoi {}

class InitEvent extends EventBoi {}

class SomeEvent extends EventBoi {}

class BlocBoi extends Bloc<EventBoi, MainState> {
  BlocBoi() : super(MainState());

  @override
  Stream<MainState> mapEventToState(EventBoi event) async* {
    if (event is InitEvent) {
      // final dynamic result = await getAuthorizationTokenMethod();
      // print('---- ${result.toString()}');
      // testStream = EventChannelTutorial.getRandomNumberStream;

      //todo: fix Klarna payment + streams
      EventChannelTutorial.getRandomNumberStream.listen(
        (event) {
          print(event.toString());
        },
        // onError: (e) => print('error $e'),
      );

      yield MainState();
    }
  }
}

class _MyHomePageState extends State<MyHomePage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Text(widget.title),
        ),
        body: StreamBuilder(
          stream: EventChannelTutorial.getRandomNumberStream,
          builder: (_, __) {
          print(__.toString());
          return SizedBox();
        },),
        floatingActionButton: FloatingActionButton(
          onPressed: () async {
            var sendMap = <String, dynamic>{
              'authorizationToken':
                  'eyJ0eXAiOiJKV1QiLCJzdWJqZWN0VHlwZSI6ImN1c3RvbWVyIiwiYWxnIjoiSFMyNTYifQ.eyJzdWIiOiJlMTdiYTllNC1iMDkzLTRkMGItOTQ4Ni0xNjEzNmE3NTlmNmQiLCJyb2xlcyI6W10sImlzcyI6ImN1c3RvbWVyLWFwaSIsImlhdCI6MTYyOTIwMDc3Nn0.Ts_Wqlp35TsKHrrFz3OXA702wxRa9gX6x9N9BBWfPe8',
              'paymentSum': '1000',
              'paymentProvider': 'что-то'
            };

            dynamic result;

            result = await platform.invokeMethod(
                'getAuthorizationTokenMethod', sendMap);
          },
          tooltip: 'Increment',
          child: Icon(Icons.add),
        ));
  }
}

class MainForm extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Center(
      child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: <Widget>[
          Text(
            '...',
          ),
        ],
      ),
    );
  }
}
