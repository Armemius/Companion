<script lang="ts">
  import { Router, Route } from "svelte-routing";
  import Home from "./lib/pages/Home.svelte";
  import Header from "./lib/components/Header.svelte";

  const basePath = import.meta.env.BASE_URL;
</script>

<Router>
  <Header />
  <main>
    <Route to={`${basePath}/`} component={Home} />
  </main>
</Router>

<style>
  main {
    padding: 55px 1rem 1rem 1rem;
    min-height: 100vh;
    min-width: 100%;
  }
</style>