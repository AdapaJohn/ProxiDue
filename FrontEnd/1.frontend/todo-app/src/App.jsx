import "./App.css";
import TodoApp from "./components/Todo/TodoApp";


function App() {
  return (
    <div className="App">
      <TodoApp />
    </div>
  );
}

export default App;

// import "./App.css";
// import Counter from "./components/counter/Counter";

// function App() {
//   return (
//     <div className="App">
//       <Counter />
//     </div>
//   );
// }

// export default App;

// import { useState } from "react";
// import reactLogo from "./assets/react.svg";
// import viteLogo from "/vite.svg";
//import "./App.css";
// import LearningComponent from "./components/learningComponents/LearningComponent";
//import Counter from "./components/counter/Counter";
// import FirstComponent from "./components/learningComponents/FourthComponent";
// import SecondComponent from "./components/learningComponents/SecondComponent";
// import ThirdComponent from "./components/learningComponents/ThirdComponent";
// import FourthComponent from "./components/learningComponents/FourthComponent";
// import {FifthComponent} from "./components/learningComponents/FirstComponent";

// function App() {
//   return (
//     <div className="App">
//       {/* <LearningComponent /> */}
//       {/* <PlayingWithProps property1="value1" property2="value2"/> */}
//       {/* <Counter/> - default Props */}
//       <Counter />
//     </div>
//   );
// }

//Traditional Method for Props
// function PlayingWithProps(properties) {
//   console.log(properties)
//   console.log(properties.property1)
//   console.log(properties.property2)
//   return (
//        <div>Props</div>
//   )
// }

//Modern React Method for Props
// function PlayingWithProps({property1,property2}) {
//   console.log(property1)
//   console.log(property2)
//   return (
//        <div>Props</div>
//   )
// }

// function FirstComponent() {
//   return (
//   <div className="FirstComponent">First Component</div>
//   )
// }

// function SecondComponent() {
//   return (
//   <div className="SecondComponent">Second Component</div>
//   )
// }

// class ThirdComponent extends Component {
//   render() {
//     return (
//       <div className="ThirdComponent">Third Component</div>
//     )
//   }
// }

// class FourthComponent extends Component{
//   render() {
//     return (
//       <div className="FourthComponent">Fourth Component</div>
//     )
//   }
// }
