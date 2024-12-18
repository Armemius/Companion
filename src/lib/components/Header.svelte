<script lang="ts">
  import {
    Header,
    HeaderUtilities,
    HeaderAction,
    HeaderPanelDivider,
    HeaderPanelLink,
  } from "carbon-components-svelte";

  import Wallet from "carbon-icons-svelte/lib/Wallet.svelte";
  import UserAvatar from "carbon-icons-svelte/lib/UserAvatar.svelte";

  import { authStatusStore } from "../store/authorization";
</script>

<Header company="AI Companion" platformName="v1.0">
  <HeaderUtilities>
    <HeaderAction
      icon={UserAvatar}
      text={`Войти`}
      on:click={(ev) => ev.stopPropagation()}
    />
    <HeaderAction
      icon={Wallet}
      text={`${0}₽`}
      on:click={(ev) => ev.stopPropagation()}
    />
    <HeaderAction>
      <HeaderPanelDivider>Аккаунт</HeaderPanelDivider>
      {#if authStatusStore}
        <HeaderPanelLink>Войти</HeaderPanelLink>
        <HeaderPanelLink>Зарегистрироваться</HeaderPanelLink>
      {:else}
        <HeaderPanelLink>Профиль</HeaderPanelLink>
        <HeaderPanelLink>Пополнить баланс</HeaderPanelLink>
        <HeaderPanelLink>Настройки</HeaderPanelLink>
        <HeaderPanelLink>Помощь</HeaderPanelLink>
        <HeaderPanelLink>Выйти</HeaderPanelLink>
      {/if}

      <HeaderPanelDivider>Информация</HeaderPanelDivider>
      <HeaderPanelLink>FAQ</HeaderPanelLink>
      <HeaderPanelLink>Отзывы</HeaderPanelLink>
    </HeaderAction>
  </HeaderUtilities>
</Header>

<slot />
