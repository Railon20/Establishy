package com.example.establish;

public class server {
    const mercadopago = require('mercadopago');

// Configura tu token de acceso
mercadopago.configure({
        access_token: 'TU_ACCESS_TOKEN'
    });

app.post('/crear_preferencia', async (req, res) => {
    const { item, provider_email } = req.body;

    const preference = {
                items: [
        {
            title: item.title,
                    quantity: 1,
                currency_id: 'ARS',
                unit_price: item.price,
        },
        ],
        marketplace_fee: item.price * 0.10, // 10% de comisión para ti
                payer: {
            email: req.body.payer_email,
        },
        payment_methods: {
            excluded_payment_types: [{ id: 'atm' }],
            installments: 1
        },
        back_urls: {
            success: "https://www.tuweb.com/exito",
                    failure: "https://www.tuweb.com/fallo",
                    pending: "https://www.tuweb.com/pendiente"
        },
        auto_return: "approved",
                collector_id: YOUR_COLLECTOR_ID,
                additional_info: "Información adicional",
                marketplace: "TuNombreDeMarketplace",
                transfer_to: provider_email, // Transferencia al proveedor
    };

        try {
        const response = await mercadopago.preferences.create(preference);
            res.json({ init_point: response.body.init_point });
        } catch (error) {
            res.status(500).send(error);
        }
    });

}
