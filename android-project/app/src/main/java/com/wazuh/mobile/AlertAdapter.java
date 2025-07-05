package com.wazuh.mobile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class AlertAdapter extends RecyclerView.Adapter<AlertAdapter.AlertViewHolder> {

    private Context context;
    private List<Alert> alerts;

    public AlertAdapter(Context context, List<Alert> alerts) {
        this.context = context;
        this.alerts = alerts;
    }

    @NonNull
    @Override
    public AlertViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_alert, parent, false);
        return new AlertViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlertViewHolder holder, int position) {
        Alert alert = alerts.get(position);
        
        holder.tvAlertTitle.setText(alert.getTitle());
        holder.tvAlertDescription.setText(alert.getDescription());
        holder.tvAlertLevel.setText(alert.getLevel());
        holder.tvAlertTime.setText(alert.getTimeAgo());
        
        // Set severity indicator color
        int severityColor = ContextCompat.getColor(context, alert.getSeverityColor());
        holder.vSeverityIndicator.setBackgroundColor(severityColor);
        holder.tvAlertLevel.setTextColor(severityColor);
    }

    @Override
    public int getItemCount() {
        return alerts.size();
    }

    public void updateAlerts(List<Alert> newAlerts) {
        this.alerts = newAlerts;
        notifyDataSetChanged();
    }

    static class AlertViewHolder extends RecyclerView.ViewHolder {
        View vSeverityIndicator;
        TextView tvAlertTitle, tvAlertDescription, tvAlertLevel, tvAlertTime;

        public AlertViewHolder(@NonNull View itemView) {
            super(itemView);
            vSeverityIndicator = itemView.findViewById(R.id.vSeverityIndicator);
            tvAlertTitle = itemView.findViewById(R.id.tvAlertTitle);
            tvAlertDescription = itemView.findViewById(R.id.tvAlertDescription);
            tvAlertLevel = itemView.findViewById(R.id.tvAlertLevel);
            tvAlertTime = itemView.findViewById(R.id.tvAlertTime);
        }
    }
}
