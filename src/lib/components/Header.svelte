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
  import { navigate } from "svelte-routing";

  const basePath = import.meta.env.BASE_URL;

  const handleLogout = () => {
    authStatusStore.set(null);
    navigate(basePath);
  };

</script>

<Header company="AI Companion" platformName="v1.0">
  <HeaderUtilities>
    <HeaderAction
      icon={UserAvatar}
      text={$authStatusStore?.user.username ?? 'Войти'}
      on:click={(ev) => ev.stopPropagation()}
    />
    {#if $authStatusStore?.authorized}
      <HeaderAction
        icon={Wallet}
        text={`Баланс: ${$authStatusStore.balance}₽`}
        on:click={(ev) => ev.stopPropagation()}
      />
    {/if}
    <HeaderAction>
      <HeaderPanelDivider>Аккаунт</HeaderPanelDivider>
      {#if !$authStatusStore?.authorized}
        <HeaderPanelLink>Войти</HeaderPanelLink>
        <HeaderPanelLink>Зарегистрироваться</HeaderPanelLink>
      {:else}
        <HeaderPanelLink>Профиль</HeaderPanelLink>
        <HeaderPanelLink>Пополнить баланс</HeaderPanelLink>
        <HeaderPanelLink>Настройки</HeaderPanelLink>
        <HeaderPanelLink>Помощь</HeaderPanelLink>
        <HeaderPanelLink on:click={handleLogout}>Выйти</HeaderPanelLink>
      {/if}

      <HeaderPanelDivider>Информация</HeaderPanelDivider>
      <HeaderPanelLink>FAQ</HeaderPanelLink>
      <HeaderPanelLink>Отзывы</HeaderPanelLink>
    </HeaderAction>
  </HeaderUtilities>
</Header>

<slot />
