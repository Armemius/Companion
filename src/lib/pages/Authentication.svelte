<script lang="ts">
  import { Button, TextInput, Form, FormGroup } from "carbon-components-svelte";
  import { api } from "../api";
  import { authStatusStore } from "../store/authorization";
  import { onMount } from "svelte";
  import { navigate } from "svelte-routing";

  const basePath = import.meta.env.BASE_URL;

  let login = "";
  let password = "";
  let username = "";
  let isLogin = true;

  onMount(() => {
    const urlParams = new URLSearchParams(window.location.search);
    isLogin = urlParams.get("action") !== "register";
  });

  const handleSubmit = async (ev) => {
    ev.preventDefault();
    if (isLogin) {
      const res = await api.post("/auth/login", { login, password });
      const username = (await api.get("/auth/username", {
        headers: {
          Authorization: `Bearer ${res.data.token}`,
        },
      })).data;
      const balance = 10000; // TODO
      authStatusStore.set({
        authorized: true,
        token: res.data.token,
        balance: balance,
        user: {
          login,
          username, // TOFO
        },
      });
      navigate(basePath);
    } else {
      const res = await api.post("/auth/register", {
        login,
        username,
        password,
      });
      const balance = 10000; // TODO
      authStatusStore.set({
        authorized: true,
        token: res.data.token,
        balance: balance,
        user: {
          login,
          username,
        },
      });
      navigate(basePath);
    }
  };
</script>

<section>
  <div class="form-container">
    <Form on:submit={handleSubmit}>
      {#if isLogin}
        <h3>Вход</h3>
        <FormGroup>
          <TextInput bind:value={login} id="login" labelText="Логин" required />
          <TextInput
            bind:value={password}
            id="password"
            labelText="Пароль"
            type="password"
            required
          />
        </FormGroup>
      {:else}
        <h3>Регистрация</h3>
        <FormGroup>
          <TextInput
            bind:value={username}
            id="username"
            labelText="Имя пользователя"
            minlength="5"
            maxlength="16"
            required
          />
          <TextInput
            bind:value={login}
            id="login"
            labelText="Логин"
            minlength="5"
            maxlength="16"
            required
          />
          <TextInput
            bind:value={password}
            id="password"
            labelText="Пароль"
            type="password"
            minlength="8"
            maxlength="255"
            required
          />
        </FormGroup>
      {/if}
      <div class="button-container">
        <Button type="submit" kind="tertiary"
          >{isLogin ? "Вход" : "Регистрация"}</Button
        >
        <Button
          type="button"
          kind="ghost"
          on:click={() => (isLogin = !isLogin)}
        >
          {isLogin ? "Нет аккаунта?" : "Есть аккаунт?"}
        </Button>
      </div>
    </Form>
  </div>
</section>

<style>
  section {
    display: flex;
    height: 70vh;
    justify-content: center;
    align-items: center;
  }

  .button-container {
    display: grid;
    grid-template-columns: repeat(2, 1fr);
    margin-top: 1rem;
    gap: 15px;
  }

  .form-container {
    width: 400px;
  }
</style>
