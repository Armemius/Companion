import { mount } from "svelte";
import "./app.css";
import "carbon-components-svelte/css/all.css";
import "carbon-components-svelte/css/white.css";
import App from "./App.svelte";

const app = mount(App, {
  target: document.getElementById("app")!,
});

export default app;
