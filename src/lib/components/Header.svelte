<script lang="ts">
  import {
    Header,
    HeaderUtilities,
    HeaderAction,
    HeaderPanelDivider,
    HeaderPanelLink,
    SkipToContent,
  } from "carbon-components-svelte";

  import Wallet from "carbon-icons-svelte/lib/Wallet.svelte";
  import UserAvatar from "carbon-icons-svelte/lib/UserAvatar.svelte";

  import { authStatusStore } from "../store/authorization";
  import { navigate } from "svelte-routing";
  import { onMount } from "svelte";

  import SideNavBar from "./SideNavBar.svelte";

  const basePath = import.meta.env.BASE_URL;

  let hamburgerMenuActive = $state(true);
  let sideNavOpened = $derived(
    hamburgerMenuActive && $authStatusStore?.authorized
  );

  const handleLogout = () => {
    authStatusStore.set(null);
    navigate(basePath);
  };
</script>

<Header
  company="AI Companion"
  platformName="v1.0"
  persistentHamburgerMenu={$authStatusStore?.authorized}
  bind:isSideNavOpen={hamburgerMenuActive}
>
  <svelte:fragment slot="skip-to-content">
    <SkipToContent />
  </svelte:fragment>

  <HeaderUtilities>
    {#if $authStatusStore?.authorized}
      <HeaderAction
        icon={UserAvatar}
        text={$authStatusStore?.user.username ?? "Ошибка"}
        on:click={(ev) => {
          ev.stopPropagation();
          navigate(`${basePath}/profile`);
        }}
      />
      <HeaderAction
        icon={Wallet}
        text={`Баланс: ${$authStatusStore.balance}₽`}
        on:click={(ev) => {
          ev.stopPropagation();
          navigate(`${basePath}/balance`);
        }}
      />
    {:else}
      <HeaderAction
        icon={UserAvatar}
        text={"Войти"}
        on:click={(ev) => {
          ev.stopPropagation();
          navigate(`${basePath}/auth?action=login`);
        }}
      />
    {/if}
    <HeaderAction>
      <HeaderPanelDivider>Аккаунт</HeaderPanelDivider>
      {#if !$authStatusStore?.authorized}
        <HeaderPanelLink href={`${basePath}/auth?action=login`}
          >Войти</HeaderPanelLink
        >
        <HeaderPanelLink href={`${basePath}/auth?action=register`}
          >Зарегистрироваться</HeaderPanelLink
        >
      {:else}
        <HeaderPanelLink href={`${basePath}/profile`}>Профиль</HeaderPanelLink>
        <HeaderPanelLink href={`${basePath}/balance`}
          >Пополнить баланс</HeaderPanelLink
        >
        <HeaderPanelLink href={`${basePath}/settings`}
          >Настройки</HeaderPanelLink
        >
        <HeaderPanelLink href={`${basePath}/help`}>Помощь</HeaderPanelLink>
        <HeaderPanelLink on:click={handleLogout}>Выйти</HeaderPanelLink>
      {/if}

      <HeaderPanelDivider>Информация</HeaderPanelDivider>
      <HeaderPanelLink href={`${basePath}/help/faq`}>FAQ</HeaderPanelLink>
      <HeaderPanelLink href={`${basePath}/help/feedback`}
        >Отзывы</HeaderPanelLink
      >
    </HeaderAction>
  </HeaderUtilities>
</Header>

<SideNavBar sideNavOpened={sideNavOpened} />
