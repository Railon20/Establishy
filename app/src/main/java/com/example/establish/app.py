from flask import Flask, request, jsonify
from flask_cors import CORS
import mercadopago

app = Flask(__name__)
CORS(app)  # Habilitar CORS para permitir solicitudes desde la app móvil

# Configura tus credenciales de Mercado Pago
sdk = mercadopago.SDK("TU_ACCESS_TOKEN")  # Reemplaza con tu Access Token

@app.route('/create_preference', methods=['POST'])
def create_preference():
    try:
        # Obtener datos del cliente
        data = request.json
        order_id = data.get("orderId")
        total_amount = data.get("totalAmount")

        # Configurar la preferencia de pago
        preference = {
            "items": [
                {
                    "title": f"Pedido #{order_id}",
                    "quantity": 1,
                    "unit_price": total_amount,
                }
            ],
            "back_urls": {
                "success": "https://www.tusitio.com/success",
                "failure": "https://www.tusitio.com/failure",
                "pending": "https://www.tusitio.com/pending",
            },
            "auto_return": "approved",
            "payment_methods": {
                "installments": 1,  # Máximo de cuotas
            },
            "marketplace": "Establish",
            "marketplace_fee": total_amount * 0.1,  # Comisión del 10%
        }

        # Crear la preferencia en Mercado Pago
        preference_response = sdk.preference().create(preference)
        preference_id = preference_response["response"]["id"]

        # Respuesta exitosa
        return jsonify({"preferenceId": preference_id})

    except Exception as e:
        # Manejo de errores
        return jsonify({"error": str(e)}), 500


if __name__ == '__main__':
    app.run(debug=True, port=5000)
