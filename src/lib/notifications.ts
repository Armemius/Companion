import { toast } from '@zerodevx/svelte-toast'

export const success = (message: string) => {
    toast.push(message, {
        theme: {
            '--toastBackground': '#55aa55',
            '--toastColor': 'white',
            '--toastBarBackground': 'white'
        }
    });
}

export const warning = (message: string) => {
    toast.push(message, {
        theme: {
            '--toastBackground': '#f5b642',
            '--toastColor': 'white',
            '--toastBarBackground': 'white'
        }
    });
}

export const error = (message: string) => {
    toast.push(message, {
        theme: {
            '--toastBackground': '#ff5555',
            '--toastColor': 'white',
            '--toastBarBackground': 'white',
        }
    });
}
