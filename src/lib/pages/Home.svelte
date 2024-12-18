<script lang="ts">
  import { onMount } from 'svelte';
    import ROCKET_GIF from '../../assets/rocket.gif';

    let rocketContainer: HTMLImageElement;

    onMount(() => {
        rocketContainer.src = "";
        setTimeout(() => {
            rocketContainer.src = ROCKET_GIF;
        }, 1000);
        setTimeout(() => {
            rocketContainer.src = "";
        }, 5700);
    })
</script>

<div class="container">
  <h3>Ваш персональный</h3>
  <h1>AI Companion</h1>
  <span>Powered by ChatGPT</span>
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

  span {
    opacity: 0;
    animation: fadeIn 0.1s 2.32s forwards;
  }

  .container {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    height: 50vh;
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
