<script lang="ts">
  import { Router, Route } from "svelte-routing";
  import Home from "./lib/pages/Home.svelte";
  import Authentication from "./lib/pages/Authentication.svelte";
  import Header from "./lib/components/Header.svelte";
  import { Theme } from "carbon-components-svelte";
  
  const basePath = import.meta.env.BASE_URL;
</script>

<Theme
  theme="white"
  tokens={{
    "interactive": "#020202",
    "interactive-01": "#020202",
    "interactive-02": "#020202",
    "interactive-03": "#020202",
    "interactive-04": "#020202",
    "link-01": "#020202",
    "link-02": "#020202e8",
    "inverse-link": "#020202e8",
    "focus": "#020202",
    "hover-primary": "#020202",
    "active-primary": "#020202e8",
    "hover-primary-text": "#020202",
    "hover-tertiary": "#020202",
    "active-tertiary": "#020202e8",
  }}
/>

<Header />
  <main>
    <Router basepath={basePath}>  
      <Route path="auth" component={Authentication} />
      <Route path="/" component={Home} />
    </Router>
  </main>


<style>
  main {
    padding: 55px 1rem 1rem 1rem;
    min-height: 100vh;
    min-width: 100%;
  }
</style>