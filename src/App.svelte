<script lang="ts">
  import { Router, Route } from "svelte-routing";
  import Home from "./lib/pages/Home.svelte";
  import Header from "./lib/components/Header.svelte";

  const basePath = import.meta.env.BASE_URL;
  
</script>

<Router>
  <Header />
  <main class="w-full p-4">
    <Route to={`${basePath}/`} component={Home} />
  </main>
</Router>