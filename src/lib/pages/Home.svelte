<script lang="ts">
  import { onMount } from "svelte";
  import ROCKET_GIF from "../../assets/rocket.gif";
  import { Button } from "carbon-components-svelte";
  import { authStatusStore } from "../store/authorization";
  import { navigate } from 'svelte-routing';

  const basePath = import.meta.env.BASE_URL;

  let rocketContainer: HTMLImageElement;

  onMount(() => {
    if ($authStatusStore?.authorized) {
      navigate(`${basePath}/chats`);
    }
    rocketContainer.src = "";
    setTimeout(() => {
      rocketContainer.src = ROCKET_GIF;
    }, 1000);
    setTimeout(() => {
      rocketContainer.src = "";
    }, 5700);
  });
</script>

<div class="container">
  <h3>Ваш персональный</h3>
  <h1>AI Companion</h1>
  <span class="appear-after-animation">Powered by ChatGPT</span>
  <div class="appear-after-animation button-container">
    {#if !$authStatusStore?.authorized}
      <Button href={`${basePath}/auth?action=login`} kind="tertiary"
        >Вход</Button
      >
      <Button href={`${basePath}/auth?action=register`} kind="tertiary"
        >Регистрация</Button
      >
    {/if}
  </div>
</div>
<div class="rocket">
  <img src={ROCKET_GIF} bind:this={rocketContainer} alt="na kiev.gif" />
</div>

<style>
  h3 {
    opacity: 0;
    font-weight: 100;
    font-size: 2rem;
    animation: fadeIn 0.6s ease-in forwards;
  }

  h1 {
    opacity: 0;
    font-weight: 100;
    font-size: 4rem;
    animation: fadeIn 0.6s 0.66s ease-in forwards;
  }

  .appear-after-animation {
    opacity: 0;
    animation: fadeIn 0.005s 3.62s forwards;
  }

  .container {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    height: 50vh;
  }

  .button-container {
    display: grid;
    grid-template-columns: repeat(2, 150px);
    gap: 10px;
    padding: 15px;
  }

  .rocket {
    position: absolute;
    display: flex;
    justify-content: center;
    align-items: center;
    top: 0;
    bottom: 0;
    left: 0;
    right: 0;
    overflow: hidden;
    pointer-events: none;
  }

  .rocket img {
    height: 120%;
    transform: translateY(-5%) translateX(50px);
  }

  @keyframes fadeIn {
    from {
      opacity: 0;
      transform: translateY(-40px);
    }

    to {
      opacity: 1;
      transform: translateY(0);
    }
  }
</style>
