package com.wazuh.mobile;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvAgents, rvAlerts;
    private SwipeRefreshLayout swipeRefreshLayout;
    private BottomNavigationView bottomNavigation;
    private AgentAdapter agentAdapter;
    private AlertAdapter alertAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();
        setupRecyclerViews();
        setupBottomNavigation();
        loadDashboardData();
    }

    private void initializeViews() {
        rvAgents = findViewById(R.id.rvAgents);
        rvAlerts = findViewById(R.id.rvAlerts);
        bottomNavigation = findViewById(R.id.bottomNavigation);
    }

    private void setupRecyclerViews() {
        // Setup Agents RecyclerView
        rvAgents.setLayoutManager(new LinearLayoutManager(this));
        agentAdapter = new AgentAdapter(this, new ArrayList<>());
        rvAgents.setAdapter(agentAdapter);

        // Setup Alerts RecyclerView
        rvAlerts.setLayoutManager(new LinearLayoutManager(this));
        alertAdapter = new AlertAdapter(this, new ArrayList<>());
        rvAlerts.setAdapter(alertAdapter);
    }

    private void setupBottomNavigation() {
        bottomNavigation.setSelectedItemId(R.id.nav_dashboard);
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_dashboard:
                        // Already on dashboard
                        return true;
                    case R.id.nav_alerts:
                        // Navigate to alerts (implement later)
                        return true;
                    case R.id.nav_events:
                        // Navigate to events (implement later)
                        return true;
                    case R.id.nav_settings:
                        // Navigate to settings (implement later)
                        return true;
                }
                return false;
            }
        });
    }

    private void loadDashboardData() {
        // Load mock data for agents
        List<Agent> agents = createMockAgents();
        agentAdapter.updateAgents(agents);

        // Load mock data for alerts
        List<Alert> alerts = createMockAlerts();
        alertAdapter.updateAlerts(alerts);
    }

    private List<Agent> createMockAgents() {
        List<Agent> agents = new ArrayList<>();
        agents.add(new Agent("Web Server 01", "192.168.1.100", Agent.Status.ACTIVE, Agent.Type.SERVER));
        agents.add(new Agent("Database Server", "192.168.1.101", Agent.Status.ACTIVE, Agent.Type.DATABASE));
        agents.add(new Agent("Workstation 03", "192.168.1.150", Agent.Status.INACTIVE, Agent.Type.WORKSTATION));
        return agents;
    }

    private List<Alert> createMockAlerts() {
        List<Alert> alerts = new ArrayList<>();
        alerts.add(new Alert(
            "Multiple Failed SSH Login Attempts",
            "25 failed login attempts detected from IP 185.234.xxx.xxx targeting root account on Web Server 01",
            "CRITICAL • Level 15",
            "5 min ago",
            Alert.Severity.CRITICAL
        ));
        alerts.add(new Alert(
            "Suspicious Process Execution",
            "Unusual process 'crypto-miner.exe' detected",
            "HIGH • Level 12",
            "12 min ago",
            Alert.Severity.HIGH
        ));
        return alerts;
    }

    @Override
    public void onBackPressed() {
        // Move app to background instead of closing
        moveTaskToBack(true);
    }
}
