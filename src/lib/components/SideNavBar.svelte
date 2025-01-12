<script lang="ts">
  import {
    SideNav,
    SideNavItems,
    SideNavDivider,
    SideNavLink,
    SideNavMenuItem
  } from "carbon-components-svelte";

  export let sideNavOpened = false;
</script>

<SideNav isOpen={sideNavOpened} style="width: 256px">
  <SideNavItems>
    <SideNavLink>Создать компаньона...</SideNavLink>
    <br>
    <SideNavMenuItem>Адольф Гитлер</SideNavMenuItem>
    <SideNavDivider>Amogus</SideNavDivider>
    <SideNavLink>Диалог 1</SideNavLink>
    <SideNavLink>Диалог 2</SideNavLink>
    <SideNavLink>Диалог 3</SideNavLink>
    <br />
    <SideNavLink>Новый диалог...</SideNavLink>
    <br />
    <br />
    <SideNavMenuItem>Махатма Ганди</SideNavMenuItem>
    <SideNavDivider>Amogus</SideNavDivider>
    <SideNavLink>Диалог 1</SideNavLink>
    <SideNavLink>Диалог 2</SideNavLink>
    <SideNavLink>Диалог 3</SideNavLink>
    <br />
    <SideNavLink>Новый диалог...</SideNavLink>
    <br />
    <br />
  </SideNavItems>
</SideNav>