import React, { useState } from "react";
import { useAuth } from "./security/AuthContext";
import { sendOrderEmail, testEmail } from "./api/EmailService";

function EmailComponent() {
  // Form state
  const [to, setTo] = useState("");
  const [orderDetails, setOrderDetails] = useState("");
  // Alert/message states
  const [message, setMessage] = useState("");
  const [testMailMessage, setTestMailMessage] = useState("");
  // Auth context if needed for JWT, etc.
  const authContext = useAuth();

  // Handler: Send Order Email
  const handleSendEmail = async (e) => {
    e.preventDefault();
    setMessage("");
    try {
      const response = await sendOrderEmail({ to, orderDetails });
      setMessage(response.data);
      setTo("");
      setOrderDetails("");
    } catch (error) {
      setMessage(
        "Failed to send email: " +
          (error.response?.data || error.message)
      );
    }
  };

  // Handler: Test Backend Email (GET /test/email)
  const handleTestEmail = async () => {
    setTestMailMessage("");
    try {
      const response = await testEmail();
      setTestMailMessage(response.data);
    } catch (error) {
      setTestMailMessage(
        "Failed to send test email: " +
          (error.response?.data || error.message)
      );
    }
  };

  return (
    <div className="container" style={{ maxWidth: 600, margin: "auto" }}>
      <h2>Send Order Confirmation Email</h2>
      <form onSubmit={handleSendEmail}>
        <div className="form-group">
          <label>Recipient Email:</label>
          <input
            type="email"
            className="form-control"
            value={to}
            onChange={(e) => setTo(e.target.value)}
            required
            placeholder="Enter recipient email"
          />
        </div>
        <div className="form-group">
          <label>Order Details:</label>
          <textarea
            className="form-control"
            value={orderDetails}
            onChange={(e) => setOrderDetails(e.target.value)}
            required
            placeholder="Enter order details"
          />
        </div>
        <button type="submit" className="btn btn-primary mt-2">
          Send Email
        </button>
      </form>
      {message && (
        <div className="alert alert-info mt-3">{message}</div>
      )}

      <hr />

      <h3>Test Backend Email (No Auth Required)</h3>
      <button className="btn btn-secondary" onClick={handleTestEmail}>
        Send Test Email
      </button>
      {testMailMessage && (
        <div className="alert alert-info mt-3">{testMailMessage}</div>
      )}
    </div>
  );
}

export default EmailComponent;